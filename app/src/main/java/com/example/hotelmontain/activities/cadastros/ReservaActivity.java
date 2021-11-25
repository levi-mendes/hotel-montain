package com.example.hotelmontain.activities.cadastros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.example.hotelmontain.R;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.QuartoDao;
import com.example.hotelmontain.database.dao.ReservaDao;
import com.example.hotelmontain.database.entity.Quarto;
import com.example.hotelmontain.database.entity.Reserva;
import com.example.hotelmontain.util.AlertUtil;
import com.example.hotelmontain.util.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static com.example.hotelmontain.util.EditTextUtil.getInt;
import static com.example.hotelmontain.util.EditTextUtil.getTexto;
import static java.lang.String.valueOf;

public class ReservaActivity extends AppCompatActivity {

    private TextInputEditText etDataHora;
    private ArrayAdapter quartosAdapter;
    private AppCompatSpinner spQuartos;
    private TextInputEditText etNumHospedes;
    private int mQuartoSelecionado;
    private Reserva mReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Cadastro de Reservas");

        etNumHospedes = findViewById(R.id.et_num_hospedes);
        etDataHora = findViewById(R.id.et_data_hora);

        etDataHora.setOnTouchListener((v, event)-> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    requestData();
                }
                return false;
        });

        spQuartos = findViewById(R.id.sp_num_quarto);

        quartosAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, numQuartos());
        quartosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spQuartos.setAdapter(quartosAdapter);
        spQuartos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mQuartoSelecionado = Integer.parseInt(quartosAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        findViewById(R.id.bt_salvar).setOnClickListener(v -> {
            if (mReserva == null) {
                salvar();
            } else {
                atualizar();
            }
        });

        mReserva = (Reserva)getIntent().getSerializableExtra("reserva");

        if (mReserva != null) {
            carregarDadosReserva();
        }
    }

    private void atualizar() {
        ReservaDao dao = HotelMontainDatabase.getInstance(this).reservaDao();

        try {
            mReserva.numQuarto = mQuartoSelecionado;
            mReserva.numHospedes = getInt(etNumHospedes);
            mReserva.dataHorario = getTexto(etDataHora);

            dao.atualizar(mReserva.numReserva, mReserva.numHospedes, mReserva.dataHorario, mReserva.numQuarto);

            new AlertDialog.Builder(this)
                    .setMessage("Quarto ATUALIZADO com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("salvar", e.getMessage());
            AlertUtil.showSucesso(this, "Erro ao tentar inserir quarto !!!");
        }
    }

    private void selectQuartoSelecionado() {
        for (int index = 0; index < quartosAdapter.getCount(); index++) {
            String item = quartosAdapter.getItem(index).toString();

            if (item.equals(valueOf(mReserva.numQuarto))) {
                spQuartos.setSelection(index);
            }
        }
    }

    private void carregarDadosReserva() {
        etNumHospedes.setText(valueOf(mReserva.numHospedes));
        etDataHora.setText(mReserva.dataHorario);
        selectQuartoSelecionado();
    }

    private List<String> numQuartos() {
        QuartoDao dao = HotelMontainDatabase.getInstance(this).quartoDao();
        List<String> retorno = new ArrayList<>();
        List<Quarto> quartos = dao.getQuartos();

        for (Quarto q : quartos) {
            retorno.add(valueOf(q.numQuarto));
        }

        return retorno;
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
                    etDataHora.setText(String.format("%s/%s/%s", dayOfMonth, monthOfYear + 1, year));

    private void salvar() {
        ReservaDao dao = HotelMontainDatabase.getInstance(this).reservaDao();

        try {
            dao.inser(reserva());

            new AlertDialog.Builder(this)
                    .setMessage("RESERVA cadastrada com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("salvar", e.getMessage());
            AlertUtil.showSucesso(this, "Erro ao tentar fazer reserva !!!");
        }
    }

    private Reserva reserva() {
        Reserva retorno = new Reserva();

        retorno.dataHorario = getTexto(etDataHora);
        retorno.numHospedes = getInt(etNumHospedes);
        retorno.numQuarto = mQuartoSelecionado;

        return retorno;
    }
}