package com.example.hotelmontain.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.hotelmontain.R;
import com.example.hotelmontain.database.Funcionario;
import com.example.hotelmontain.database.FuncionarioDao;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.util.AlertUtil;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FuncionarioActivity extends AppCompatActivity {

    private Button btDataNascimento;

    TextInputEditText etNome;
    TextInputEditText etEmail;
    TextInputEditText etCpf;
    TextInputEditText etEstadoCivil;
    TextInputEditText etEcp;
    TextInputEditText etCargo;
    TextInputEditText etTelefone;
    TextInputEditText etEndereco;
    TextInputEditText etNumero;
    TextInputEditText etCidade;
    TextInputEditText etEstado;
    RadioGroup rgSexo;
    RadioButton rbMasculino, rbFeminino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        etNome = findViewById(R.id.et_nome);
        etEmail = findViewById(R.id.et_email);
        etCpf = findViewById(R.id.et_cpf);
        etEstadoCivil = findViewById(R.id.et_estado_civil);
        etEcp = findViewById(R.id.et_cep);
        etCargo = findViewById(R.id.et_cargo);
        etTelefone = findViewById(R.id.et_telefone);
        etEndereco = findViewById(R.id.et_endereco);
        etNumero = findViewById(R.id.et_numero);
        etCidade = findViewById(R.id.et_cidade);
        etEstado = findViewById(R.id.et_estado);
        rgSexo = findViewById(R.id.rg_sexo);
        rbMasculino = findViewById(R.id.rb_masculino);
        rbFeminino = findViewById(R.id.rb_feminino);
        btDataNascimento = findViewById(R.id.bt_data_nascimento);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Funcionário");

        Funcionario func = (Funcionario)getIntent().getSerializableExtra("funcionario");

        if (func != null) {
            carregarFuncionario(func);
        }

        btDataNascimento.setText(dataNascimento());
        btDataNascimento.setOnClickListener(v-> {
            Calendar calendario = Calendar.getInstance();

            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(this, mDateSetListener, ano, mes, dia).show();
        });

        findViewById(R.id.bt_salvar).setOnClickListener(v -> {
            Funcionario funcionario = funcionario();
            try {
                salvar(funcionario);
                AlertUtil.showSucesso(this, "------- Funcionario salvo com sucesso !!! ------");

            } catch (Exception e) {
                Log.e("Erro", e.getMessage());
                AlertUtil.showAlert(this, "Erro ao tentar salvar funcionário");
            }
        });
    }

    private void carregarFuncionario(Funcionario funcionario) {
        etNome.setText(funcionario.nome);
        etEmail.setText(funcionario.email);
        etCpf.setText(funcionario.cpf);
        etEstadoCivil.setText(funcionario.estadoCivil);
        etEcp.setText(funcionario.cep);
        etCargo.setText(funcionario.cargo);
        etTelefone.setText(funcionario.telefone);
        etEndereco.setText(funcionario.endereco);
        etNumero.setText(funcionario.numero);
        etCidade.setText(funcionario.cidade);
        etEstado.setText(funcionario.estado);
        btDataNascimento.setText(funcionario.dataNascimento);
        rbMasculino.setChecked(funcionario.sexo == 1);
        rbFeminino.setChecked(funcionario.sexo == 0);
    }

    private Funcionario funcionario() {
        Funcionario funcionario = new Funcionario();

        funcionario.nome = etNome.getText().toString();
        funcionario.email = etEmail.getText().toString();
        funcionario.dataNascimento = btDataNascimento.getText().toString();
        funcionario.cpf = etCpf.getText().toString();
        funcionario.sexo = rgSexo.getCheckedRadioButtonId() == R.id.rb_masculino ? 1 : 2;
        funcionario.estadoCivil = etEstadoCivil.getText().toString();
        funcionario.cep = etEcp.getText().toString();
        funcionario.cargo = etCargo.getText().toString();
        funcionario.telefone = etTelefone.getText().toString();
        funcionario.endereco = etEndereco.getText().toString();
        funcionario.numero = etNumero.getText().toString();
        funcionario.cidade = etCidade.getText().toString();
        funcionario.estado = etEstado.getText().toString();

        return funcionario;
    }

    private void salvar(Funcionario funcionario) {
        HotelMontainDatabase database = HotelMontainDatabase.getInstance(this);
        FuncionarioDao funcionarioDao = database.funcionarioDao();

        funcionarioDao.inserir(funcionario);
    }

    private String dataNascimento() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

    private final DatePickerDialog.OnDateSetListener mDateSetListener =
            (view, year, monthOfYear, dayOfMonth) ->
                    btDataNascimento.setText(String.format("%s/%s/%s", dayOfMonth, monthOfYear, year));
}