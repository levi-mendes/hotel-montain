package com.example.hotelmontain.activities.listas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.ReservaActivity;
import com.example.hotelmontain.adapter.ListaReservasAdapter;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.ReservaDao;
import com.example.hotelmontain.database.entity.Reserva;

import java.util.List;

public class ListaReservasActivity extends AppCompatActivity implements ListaReservasAdapter.OnReservaDelete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Lista de Reservas");

        findViewById(R.id.fab_adicionar_reserva).setOnClickListener(v ->
                startActivity(new Intent(this, ReservaActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarReservas();
    }

    private void carregarReservas() {
        findViewById(R.id.pb_loading).setVisibility(View.VISIBLE);
        RecyclerView rvReservas = findViewById(R.id.rv_reserva);

        ReservaDao dao = HotelMontainDatabase.getInstance(this).reservaDao();

        List<Reserva> reservas = dao.getReservas();

        findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);

        if (reservas == null || reservas.isEmpty()) {
            findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
            rvReservas.setAdapter(null);
            return;
        }

        findViewById(R.id.tv_empty).setVisibility(View.INVISIBLE);
        rvReservas.setAdapter(new ListaReservasAdapter(this, reservas, this));
    }

    @Override
    public void remover(Reserva reserva) {
        carregarReservas();
    }
}