package com.example.hotelmontain.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.hotelmontain.PesquisaGeralActivity;
import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.cadastros.CheckInActivity;
import com.example.hotelmontain.activities.listas.ListaFornecedoresActivity;
import com.example.hotelmontain.activities.listas.ListaFuncionariosActivity;
import com.example.hotelmontain.activities.listas.ListaHospedeActivity;
import com.example.hotelmontain.activities.listas.ListaQuartosActivity;
import com.example.hotelmontain.activities.listas.ListaReservasActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Inicio");

        findViewById(R.id.bt_pesquisa).setOnClickListener(v ->
                startActivity(new Intent(this, PesquisaGeralActivity.class)));

        findViewById(R.id.bt_logout).setOnClickListener(v -> finish());
        findViewById(R.id.bt_funcionario).setOnClickListener(v -> {
            startActivity(new Intent(this, ListaFuncionariosActivity.class));
        });

        findViewById(R.id.bt_fornecedores).setOnClickListener(v -> {
            startActivity(new Intent(this, ListaFornecedoresActivity.class));
        });

        findViewById(R.id.bt_hospedes).setOnClickListener(v -> {
            startActivity(new Intent(this, ListaHospedeActivity.class));
        });

        findViewById(R.id.bt_quartos).setOnClickListener(v ->
                startActivity(new Intent(this, ListaQuartosActivity.class)));

        findViewById(R.id.bt_reservas).setOnClickListener(v ->
                startActivity(new Intent(this, ListaReservasActivity.class)));

        findViewById(R.id.bt_checkin).setOnClickListener(v ->
                startActivity(new Intent(this, CheckInActivity.class)));

//        findViewById(R.id.bt_checkout).setOnClickListener(v ->
//                startActivity(new Intent(this, CheckOutActivity.class)));
    }
}