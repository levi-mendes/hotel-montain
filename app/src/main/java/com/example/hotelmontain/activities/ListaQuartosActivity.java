package com.example.hotelmontain.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.hotelmontain.R;
import com.example.hotelmontain.adapter.ListaQuartosAdapter;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.Quarto;
import com.example.hotelmontain.database.QuartoDao;

import java.util.List;

public class ListaQuartosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quartos);

        carregarQuartos();
    }

    private void carregarQuartos() {
        QuartoDao dao = HotelMontainDatabase.getInstance(this).quartoDao();

        List<Quarto> quartos = dao.getQuartos();

        findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);

        if (quartos == null || quartos.isEmpty()) {
            findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
            return;
        }

        RecyclerView rvQuartos = findViewById(R.id.rv_quartos);
        rvQuartos.setAdapter(new ListaQuartosAdapter(quartos));
    }
}