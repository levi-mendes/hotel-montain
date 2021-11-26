package com.example.hotelmontain.activities.cadastros;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import com.example.hotelmontain.R;
import com.example.hotelmontain.util.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;
import static java.lang.String.format;

public class CheckInActivity extends AppCompatActivity {

    private TextInputEditText etData;
    private TextInputEditText etHora;
    private TextInputEditText etRgCpfHospede;
    private TextInputEditText etNumQuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Check-In");

        etRgCpfHospede = findViewById(R.id.et_cpf_rg_hospede);
        etNumQuarto = findViewById(R.id.et_num_quarto);
        etData = findViewById(R.id.et_data_entrada);
        etHora = findViewById(R.id.et_hora_entrada);

        findViewById(R.id.bt_excluir).setOnClickListener(v -> {
            ToastUtil.show(this, "Excluído com sucesso!!");
            finish();
        });
        findViewById(R.id.bt_reservar).setOnClickListener(v -> {
            ToastUtil.show(this, "Reservado com sucesso!!");
            finish();
        });

        etData.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestData();
            }
            return false;
        });

        etHora.setOnTouchListener((v, event)-> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                requestHora();
            }
            return false;
        });
    }

    private void requestHora() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) ->
                etHora.setText(format("%s:%s", selectedHour, selectedMinute)), hour, minute, true);
        mTimePicker.setTitle("Informe a hora da entrada");
        mTimePicker.show();
    }

    private void requestData() {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, mDateSetListener, ano, mes, dia).show();
    }

    private final DatePickerDialog.OnDateSetListener mDateSetListener =
            (view, year, monthOfYear, dayOfMonth) ->
                    etData.setText(format("%s/%s/%s", dayOfMonth, monthOfYear + 1, year));
}