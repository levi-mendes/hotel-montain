package com.example.hotelmontain.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.hotelmontain.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilLogin;
    private TextInputLayout tilSenha;
    private TextInputEditText etLogin;
    private TextInputEditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Área de Login");

        tilLogin = findViewById(R.id.til_login);
        tilSenha = findViewById(R.id.til_senha);

        etLogin = findViewById(R.id.et_login);
        etSenha = findViewById(R.id.et_senha);

        etLogin.setText("admin");
        etSenha.setText("admin");

        findViewById(R.id.bt_entrar).setOnClickListener(v -> login());
    }

    private void login() {
        boolean validation = true;

        if (etLogin.getText().toString().isEmpty()) {
            tilLogin.setError("Campo obrigatorio");
            validation = false;

        } else {
            tilLogin.setError(null);
        }

        if (etSenha.getText().toString().isEmpty()) {
            tilSenha.setError("Campo obrigatorio");
            validation = false;

        } else {
            tilSenha.setError(null);
        }

        if (validation) {
            etLogin.getText().clear();
            etSenha.getText().clear();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}