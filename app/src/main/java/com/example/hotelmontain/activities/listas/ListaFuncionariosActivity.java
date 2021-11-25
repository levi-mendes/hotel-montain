package com.example.hotelmontain.activities.listas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.FuncionarioActivity;
import com.example.hotelmontain.adapter.ListaFuncionariosAdapter;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.FuncionarioDao;
import com.example.hotelmontain.database.entity.Funcionario;

import java.util.List;

public class ListaFuncionariosActivity extends AppCompatActivity implements ListaFuncionariosAdapter.OnFuncionarioDelete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionarios);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Lista de Funcionários");

        findViewById(R.id.fab_adicionar_funcionario).setOnClickListener(v ->
                startActivity(new Intent(this, FuncionarioActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        findViewById(R.id.pb_loading).setVisibility(View.VISIBLE);
        RecyclerView rvFuncionarios = findViewById(R.id.rv_funcionario);

        FuncionarioDao dao = HotelMontainDatabase.getInstance(this).funcionarioDao();

        List<Funcionario> funcionarios = dao.getFuncionarios();

        findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);

        if (funcionarios == null || funcionarios.isEmpty()) {
            findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
            rvFuncionarios.setAdapter(null);
            return;
        }

        findViewById(R.id.tv_empty).setVisibility(View.INVISIBLE);
        rvFuncionarios.setAdapter(new ListaFuncionariosAdapter(this, funcionarios, this));
    }

    @Override
    public void remover(Funcionario funcionario) {
        carregarFuncionarios();
    }
}