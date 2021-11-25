package com.example.hotelmontain.activities.listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.HospedeActivity;
import com.example.hotelmontain.adapter.ListaHospedesAdapter;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.HospedeDao;
import com.example.hotelmontain.database.entity.Hospede;

import java.util.List;

public class ListaHospedeActivity extends AppCompatActivity implements ListaHospedesAdapter.OnHospedeDelete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hospedes);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Lista de Hóspedes");

        findViewById(R.id.fab_adicionar_hospedes).setOnClickListener(v ->
                startActivity(new Intent(this, HospedeActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarHospedes();
    }

    private void carregarHospedes() {
        findViewById(R.id.pb_loading).setVisibility(View.VISIBLE);
        RecyclerView rvHospedes = findViewById(R.id.rv_hospedes);

        HospedeDao dao = HotelMontainDatabase.getInstance(this).hospedeDao();

        List<Hospede> hospedes = dao.getHospedes();

        findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);

        if (hospedes == null || hospedes.isEmpty()) {
            findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
            rvHospedes.setAdapter(null);
            return;
        }

        findViewById(R.id.tv_empty).setVisibility(View.INVISIBLE);
        rvHospedes.setAdapter(new ListaHospedesAdapter(this, hospedes, this));
    }

    @Override
    public void remover(Hospede hospede) {
        carregarHospedes();
    }
}