package com.example.hotelmontain.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.hotelmontain.PesquisaGeralActivity;
import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.FuncionarioActivity;
import com.example.hotelmontain.activities.listas.ListaQuartosActivity;
import com.example.hotelmontain.activities.listas.ListaReservasActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.bt_pesquisa).setOnClickListener(v ->
                startActivity(new Intent(this, PesquisaGeralActivity.class)));

        findViewById(R.id.bt_logout).setOnClickListener(v -> finish());
        findViewById(R.id.bt_funcionario).setOnClickListener(v -> {
            startActivity(new Intent(this, FuncionarioActivity.class));
        });

        findViewById(R.id.bt_quartos).setOnClickListener(v ->
                startActivity(new Intent(this, ListaQuartosActivity.class)));

        findViewById(R.id.bt_reservas).setOnClickListener(v ->
                startActivity(new Intent(this, ListaReservasActivity.class)));
    }
}