package com.example.hotelmontain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FuncionarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        Button btDataNascimento = findViewById(R.id.bt_data_nascimento);
        btDataNascimento.setText(dataNascimento());
        btDataNascimento.setOnClickListener(v-> {
            Calendar calendario = Calendar.getInstance();

            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(this, mDateSetListener, ano, mes, dia).show();
        });
    }

    private String dataNascimento() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            (view, year, monthOfYear, dayOfMonth) -> {

//                String formatedDate = dayOfMonth + " de " +
//                String data = String.valueOf(dayOfMonth) + " /"
//                        + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
//                Toast.makeText(FuncionarioActivity.this,
//                        "DATA = " + data, Toast.LENGTH_SHORT)
//                        .show();
            };
}