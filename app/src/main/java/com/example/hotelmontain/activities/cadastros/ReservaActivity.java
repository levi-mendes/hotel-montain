package com.example.hotelmontain.activities.cadastros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import com.example.hotelmontain.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class ReservaActivity extends AppCompatActivity {

    private TextInputEditText etDataHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        etDataHora = findViewById(R.id.et_data_hora);
        etDataHora.setOnClickListener(v -> requestData());
    }

    private void requestData() {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, mDateSetListener, ano, mes, dia).show();
    }

    private final DatePickerDialog.OnDateSetListener mDateSetListener =
            (view, year, monthOfYear, dayOfMonth) ->
                    etDataHora.setText(String.format("%s/%s/%s", dayOfMonth, monthOfYear, year));
}