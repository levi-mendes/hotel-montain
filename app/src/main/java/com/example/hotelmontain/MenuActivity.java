package com.example.hotelmontain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
    }
}