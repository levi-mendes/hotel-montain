package com.example.hotelmontain.activities.cadastros;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmontain.R;
import com.example.hotelmontain.util.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import static java.lang.String.format;

public class CheckOutActivity extends AppCompatActivity {

    private TextInputEditText etDataEntrada;
    private TextInputEditText etHoraEntrada;
    private TextInputEditText etDataSaida;
    private TextInputEditText etHoraSaida;
    private TextInputEditText etRgCpfHospede;
    private TextInputEditText etNumQuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Check-In");

        etRgCpfHospede = findViewById(R.id.et_cpf_rg_hospede);
        etNumQuarto = findViewById(R.id.et_num_quarto);
        etDataEntrada = findViewById(R.id.et_data_entrada);
        etHoraEntrada = findViewById(R.id.et_hora_entrada);
        etDataSaida = findViewById(R.id.et_data_saida);
        etHoraSaida = findViewById(R.id.et_hora_saida);

        findViewById(R.id.bt_finalizar_reserva).setOnClickListener(v -> {
            ToastUtil.show(this, "Reserva finalizada!!");
            finish();
        });

        etDataEntrada.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestData(etDataEntrada);
            }
            return false;
        });

        etHoraEntrada.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestHora(etHoraEntrada);
            }
            return false;
        });

        etDataSaida.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestData(etDataSaida);
            }
            return false;
        });

        etHoraSaida.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestHora(etHoraSaida);
            }
            return false;
        });
    }

    private void requestHora(TextInputEditText inputEditText) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) ->
                inputEditText.setText(format("%s:%s", selectedHour, selectedMinute)), hour, minute, true);
        mTimePicker.setTitle("Informe a hora da entrada");
        mTimePicker.show();
    }

    private void requestData(TextInputEditText inputEditText) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                inputEditText.setText(format("%s/%s/%s", dayOfMonth, month + 1, year)),
                ano, mes, dia)
                .show();
    }

}