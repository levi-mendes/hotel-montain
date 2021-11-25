package com.example.hotelmontain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.hotelmontain.activities.cadastros.FuncionarioActivity;
import com.example.hotelmontain.database.Funcionario;
import com.example.hotelmontain.database.FuncionarioDao;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.util.AlertUtil;
import com.google.android.material.textfield.TextInputEditText;

public class PesquisaGeralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_geral);

        findViewById(R.id.bt_novo_cadastro).setOnClickListener(v-> finish());
        findViewById(R.id.bt_pesquisar).setOnClickListener(v -> {

            if (valorPesquisa().isEmpty()) {
                AlertUtil.showAlert(this, "Informe o campo para realizar a pesquisa");
                return;
            }

            Funcionario funcionario = buscasFuncionario();

            if (funcionario != null) {
                Intent intent = new Intent(this, FuncionarioActivity.class);
                intent.putExtra("funcionario", funcionario);
                startActivity(intent);
                return;
            }

        });
    }

    private String valorPesquisa() {
        return ((TextInputEditText)findViewById(R.id.et_chave_pesquisa)).getText().toString();
    }

    private Funcionario buscasFuncionario() {
        HotelMontainDatabase database = HotelMontainDatabase.getInstance(this);
        FuncionarioDao dao = database.funcionarioDao();

        Funcionario funcionario = dao.buscarPeloCpf(valorPesquisa());
        return funcionario;
    }
}