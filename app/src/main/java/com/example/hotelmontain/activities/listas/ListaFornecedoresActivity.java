package com.example.hotelmontain.activities.listas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.FornecedorActivity;
import com.example.hotelmontain.adapter.ListaFornecedoresAdapter;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.FornecedorDao;
import com.example.hotelmontain.database.entity.Fornecedor;

import java.util.List;

public class ListaFornecedoresActivity extends AppCompatActivity implements ListaFornecedoresAdapter.OnFornecedorDelete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fornecedores);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Lista de Fornecedores");

        findViewById(R.id.fab_adicionar_fornecedor).setOnClickListener(v ->
                startActivity(new Intent(this, FornecedorActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarFornecedores();
    }

    private void carregarFornecedores() {
        findViewById(R.id.pb_loading).setVisibility(View.VISIBLE);
        RecyclerView rvFornecedores = findViewById(R.id.rv_fornecedor);

        FornecedorDao dao = HotelMontainDatabase.getInstance(this).fornecedorDao();

        List<Fornecedor> fornecedores = dao.getFornecedores();

        findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);

        if (fornecedores == null || fornecedores.isEmpty()) {
            findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
            rvFornecedores.setAdapter(null);
            return;
        }

        findViewById(R.id.tv_empty).setVisibility(View.INVISIBLE);
        rvFornecedores.setAdapter(new ListaFornecedoresAdapter(this, fornecedores, this));
    }

    @Override
    public void remover(Fornecedor fornecedor) {
        carregarFornecedores();
    }
}