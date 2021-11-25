package com.example.hotelmontain.activities.listas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.QuartoActivity;
import com.example.hotelmontain.adapter.ListaQuartosAdapter;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.entity.Quarto;
import com.example.hotelmontain.database.dao.QuartoDao;
import java.util.List;

public class ListaQuartosActivity extends AppCompatActivity implements ListaQuartosAdapter.OnQuartoRemovido {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quartos);

        findViewById(R.id.fab_adicionar_quarto).setOnClickListener(v -> startActivity(new Intent(this, QuartoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarQuartos();
    }

    private void carregarQuartos() {
        findViewById(R.id.pb_loading).setVisibility(View.VISIBLE);
        RecyclerView rvQuartos = findViewById(R.id.rv_quartos);

        QuartoDao dao = HotelMontainDatabase.getInstance(this).quartoDao();

        List<Quarto> quartos = dao.getQuartos();

        findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);

        if (quartos == null || quartos.isEmpty()) {
            findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
            rvQuartos.setAdapter(null);
            return;
        }

        findViewById(R.id.tv_empty).setVisibility(View.INVISIBLE);
        rvQuartos.setAdapter(new ListaQuartosAdapter(this, quartos, this));
    }

    @Override
    public void remover(Quarto quarto) {
        carregarQuartos();
    }
}