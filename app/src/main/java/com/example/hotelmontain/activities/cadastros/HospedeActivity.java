package com.example.hotelmontain.activities.cadastros;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.hotelmontain.R;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.HospedeDao;
import com.example.hotelmontain.database.entity.Hospede;
import com.example.hotelmontain.util.AlertUtil;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class HospedeActivity extends AppCompatActivity {

    private TextInputEditText etDataHora;
    private TextInputEditText etNome;
    private TextInputEditText etEmail;
    private  TextInputEditText etCpf;
    private TextInputEditText etEstadoCivil;
    private TextInputEditText etEcp;
    private TextInputEditText etTelefone;
    private TextInputEditText etEndereco;
    private TextInputEditText etNumero;
    private TextInputEditText etCidade;
    private AppCompatSpinner spEstados;
    private RadioGroup rgSexo;
    private RadioButton rbMasculino, rbFeminino;
    private ArrayAdapter<CharSequence> adapterEstados;
    private Hospede mHospede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospede);

        etNome = findViewById(R.id.et_nome);
        etEmail = findViewById(R.id.et_email);
        etCpf = findViewById(R.id.et_cpf);
        etEstadoCivil = findViewById(R.id.et_estado_civil);
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
        getSupportActionBar().setTitle("Hospede");

        carregarEstados();

        mHospede = (Hospede) getIntent().getSerializableExtra("hospede");

        if (mHospede != null) {
            carregarFuncionario();
        }

        etDataHora.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestData();
            }
            return false;
        });

        findViewById(R.id.bt_salvar).setOnClickListener(v -> {

            if (mHospede == null) {
                inserir();
            } else {
                atualizar();
            }
        });
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

    private void carregarEstados() {
        adapterEstados = ArrayAdapter.createFromResource(this,
                R.array.array_estados, android.R.layout.simple_spinner_item);
        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstados.setAdapter(adapterEstados);
    }

    private void carregarFuncionario() {
        etNome.setText(mHospede.nome);
        etEmail.setText(mHospede.email);
        etDataHora.setText(mHospede.dataNascimento);
        etCpf.setText(mHospede.cpf);
        etEstadoCivil.setText(mHospede.estadoCivil);
        etEcp.setText(mHospede.cep);
        etTelefone.setText(mHospede.telefone);
        etEndereco.setText(mHospede.endereco);
        etNumero.setText(mHospede.numero);
        etCidade.setText(mHospede.cidade);
        selectItemEstado(mHospede.estado);
        rbMasculino.setChecked(mHospede.sexo == 1);
        rbFeminino.setChecked(mHospede.sexo == 0);
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

    private Hospede hospede() {
        Hospede h = new Hospede();

        h.nome = etNome.getText().toString();
        h.email = etEmail.getText().toString();
        h.dataNascimento = etDataHora.getText().toString();
        h.cpf = etCpf.getText().toString();
        h.sexo = rgSexo.getCheckedRadioButtonId() == R.id.rb_masculino ? 1 : 2;
        h.estadoCivil = etEstadoCivil.getText().toString();
        h.cep = etEcp.getText().toString();
        h.telefone = etTelefone.getText().toString();
        h.endereco = etEndereco.getText().toString();
        h.numero = etNumero.getText().toString();
        h.cidade = etCidade.getText().toString();
        h.estado = getSiglaEstado();

        return h;
    }

    private void atualizar() {
        HotelMontainDatabase database = HotelMontainDatabase.getInstance(this);
        HospedeDao dao = database.hospedeDao();
        Hospede func = hospede();

        try {
            dao.atualizar(func.nome, func.email, func.dataNascimento, func.sexo, func.estadoCivil,
                    func.cep, func.telefone, func.endereco, func.numero,
                    func.cidade, func.estado, func.cpf);

            new AlertDialog.Builder(this)
                    .setMessage("Hospede ATUALIZADO com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
            AlertUtil.showAlert(this, "Erro ao tentar ATUALIZAR hospede");
        }
    }

    private void inserir() {
        HotelMontainDatabase database = HotelMontainDatabase.getInstance(this);
        HospedeDao dao = database.hospedeDao();

        try {
            dao.inserir(hospede());
            new AlertDialog.Builder(this)
                    .setMessage("Hospede CADASTRADO com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
            AlertUtil.showAlert(this, "Erro ao tentar CADASTRAR hospede");
        }
    }
}