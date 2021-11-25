package com.example.hotelmontain.activities.cadastros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hotelmontain.R;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.FornecedorDao;
import com.example.hotelmontain.database.entity.Fornecedor;
import com.example.hotelmontain.util.AlertUtil;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class FornecedorActivity extends AppCompatActivity {

    private TextInputEditText etDataHora;
    private TextInputEditText etNome;
    private TextInputEditText etEmail;
    private  TextInputEditText etCnpj;
    private TextInputEditText etEcp;
    private TextInputEditText etTelefone;
    private TextInputEditText etEndereco;
    private TextInputEditText etNumero;
    private TextInputEditText etCidade;
    private AppCompatSpinner spEstados;
    private RadioGroup rgSexo;
    private RadioButton rbMasculino, rbFeminino;
    private ArrayAdapter<CharSequence> adapterEstados;
    private Fornecedor mFornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);

        etNome = findViewById(R.id.et_nome);
        etEmail = findViewById(R.id.et_email);
        etCnpj = findViewById(R.id.et_cnpj);
        etEcp = findViewById(R.id.et_cep);
        etTelefone = findViewById(R.id.et_telefone);
        etEndereco = findViewById(R.id.et_endereco);
        etNumero = findViewById(R.id.et_numero);
        etCidade = findViewById(R.id.et_cidade);
        spEstados = findViewById(R.id.sp_estados);
        rgSexo = findViewById(R.id.rg_sexo);
        rbMasculino = findViewById(R.id.rb_masculino);
        rbFeminino = findViewById(R.id.rb_feminino);
        etDataHora = findViewById(R.id.et_data_hora);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Fornecedor");

        etDataHora.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestData();
            }
            return false;
        });

        carregarEstados();

        mFornecedor = (Fornecedor) getIntent().getSerializableExtra("fornecedor");

        if (mFornecedor != null) {
            carregarnarioFornecedor();
        }

        findViewById(R.id.bt_salvar).setOnClickListener(v -> {

            if (mFornecedor == null) {
                inserir();
            } else {
                atualizar();
            }
        });
    }

    private void carregarEstados() {
        adapterEstados = ArrayAdapter.createFromResource(this,
                R.array.array_estados, android.R.layout.simple_spinner_item);
        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstados.setAdapter(adapterEstados);
    }

    private void carregarnarioFornecedor() {
        etNome.setText(mFornecedor.nome);
        etEmail.setText(mFornecedor.email);
        etDataHora.setText(mFornecedor.dataNascimento);
        etCnpj.setText(mFornecedor.cnpj);
        etEcp.setText(mFornecedor.cep);
        etTelefone.setText(mFornecedor.telefone);
        etEndereco.setText(mFornecedor.endereco);
        etNumero.setText(mFornecedor.numero);
        etCidade.setText(mFornecedor.cidade);
        selectItemEstado(mFornecedor.estado);
        rbMasculino.setChecked(mFornecedor.sexo == 1);
        rbFeminino.setChecked(mFornecedor.sexo == 0);
    }

    private void selectItemEstado(String siglaEstado) {
        for (int index = 0; index < adapterEstados.getCount(); index++) {
            if (adapterEstados.getItem(index).equals(siglaEstado)) {
                spEstados.setSelection(index);
            }
        }
    }

    private String getSiglaEstado() {
        return spEstados.getSelectedItem().toString();
    }

    private Fornecedor fornecedor() {
        Fornecedor f = new Fornecedor();

        f.nome = etNome.getText().toString();
        f.email = etEmail.getText().toString();
        f.dataNascimento = etDataHora.getText().toString();
        f.cnpj = etCnpj.getText().toString();
        f.sexo = rgSexo.getCheckedRadioButtonId() == R.id.rb_masculino ? 1 : 2;
        f.cep = etEcp.getText().toString();
        f.telefone = etTelefone.getText().toString();
        f.endereco = etEndereco.getText().toString();
        f.numero = etNumero.getText().toString();
        f.cidade = etCidade.getText().toString();
        f.estado = getSiglaEstado();

        return f;
    }

    private void atualizar() {
        HotelMontainDatabase database = HotelMontainDatabase.getInstance(this);
        FornecedorDao dao = database.fornecedorDao();
        Fornecedor func = fornecedor();

        try {
            dao.atualizar(func.nome, func.email, func.dataNascimento, func.sexo, func.estadoCivil,
                    func.cep, func.telefone, func.endereco, func.numero,
                    func.cidade, func.estado, func.cnpj);

            new AlertDialog.Builder(this)
                    .setMessage("Fornecedor ATUALIZADO com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
            AlertUtil.showAlert(this, "Erro ao tentar ATUALIZAR Fornecedor");
        }
    }

    private void inserir() {
        HotelMontainDatabase database = HotelMontainDatabase.getInstance(this);
        FornecedorDao dao = database.fornecedorDao();

        try {
            dao.inserir(fornecedor());
            new AlertDialog.Builder(this)
                    .setMessage("Fornecedor CADASTRADO com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
            AlertUtil.showAlert(this, "Erro ao tentar CADASTRAR Fornecedor");
        }
    }

    private void requestData() {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, mDateSetListener, ano, mes, dia).show();
    }

    private final DatePickerDialog.OnDateSetListener mDateSetListener =
            (view, year, monthOfYear, dayOfMonth) ->
                    etDataHora.setText(String.format("%s/%s/%s", dayOfMonth, monthOfYear + 1, year));
}