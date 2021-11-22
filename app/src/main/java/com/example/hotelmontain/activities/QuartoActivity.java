package com.example.hotelmontain.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.hotelmontain.R;

public class QuartoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Quarto");
    }
}