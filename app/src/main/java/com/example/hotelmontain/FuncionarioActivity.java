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

    private Button btDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Funcionário");

        btDataNascimento = findViewById(R.id.bt_data_nascimento);
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

                String formatedDate = weekDayDescription(dayOfMonth) + ", " + dayOfMonth +
                        " de " + monthOfYear + " de " + year;

                btDataNascimento.setText(formatedDate);
            };


    private String weekDayDescription(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "Segunda";
            case Calendar.TUESDAY:

                return "Terça";
            case Calendar.WEDNESDAY:

                return "Quarta";
            case Calendar.THURSDAY:

                return "Quinta";
            case Calendar.FRIDAY:

                return "Sexta";
            case Calendar.SATURDAY:

                return "Sábado";
            case Calendar.SUNDAY:

                return "Domingo";
        }

        return null;
    }
}