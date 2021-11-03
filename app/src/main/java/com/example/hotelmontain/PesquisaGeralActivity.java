package com.example.hotelmontain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PesquisaGeralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_geral);

        findViewById(R.id.bt_novo_cadastro).setOnClickListener(v-> finish());
    }
}