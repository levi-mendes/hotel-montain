package com.example.hotelmontain.activities.cadastros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hotelmontain.R;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.entity.Quarto;
import com.example.hotelmontain.database.dao.QuartoDao;
import com.example.hotelmontain.util.AlertUtil;
import com.google.android.material.textfield.TextInputEditText;
import static com.example.hotelmontain.util.EditTextUtil.getDouble;
import static com.example.hotelmontain.util.EditTextUtil.getInt;
import static java.lang.String.valueOf;

public class QuartoActivity extends AppCompatActivity {

    private TextInputEditText etNumCamas;
    private TextInputEditText etNumTvs;
    private TextInputEditText etAndar;
    private TextInputEditText etNumBanheiros;
    private TextInputEditText etNumQuarto;
    private TextInputEditText etStatus;
    private TextInputEditText etValor;
    private TextInputEditText etTelefone;

    private Quarto mQuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Cadastro de Quartos");

        etNumCamas = findViewById(R.id.et_num_de_camas);
        etNumTvs = findViewById(R.id.et_num_tvs);
        etAndar = findViewById(R.id.et_andar);
        etNumBanheiros = findViewById(R.id.et_num_banheiros);
        etNumQuarto = findViewById(R.id.et_num_do_quarto);
        etStatus = findViewById(R.id.et_status);
        etValor = findViewById(R.id.et_valor);
        etTelefone = findViewById(R.id.et_telefone);

        mQuarto = (Quarto)getIntent().getSerializableExtra("quarto");

        if (mQuarto != null) {
            carregarDados();
        }

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Quarto");

        findViewById(R.id.bt_salvar).setOnClickListener(v -> {
            if (mQuarto != null) {
                atualizar();

            } else {
                inserir();
            }});
        }

    private void carregarDados() {
        etNumCamas.setText(valueOf(mQuarto.numCamas));
        etNumTvs.setText(valueOf(mQuarto.numTvs));
        etAndar.setText(valueOf(mQuarto.andar));
        etNumBanheiros.setText(valueOf(mQuarto.numBanheiros));
        etNumQuarto.setText(valueOf(mQuarto.numQuarto));
        etStatus.setText(valueOf(mQuarto.status));
        etValor.setText(valueOf(mQuarto.valor));
        etTelefone.setText(valueOf(mQuarto.numTelefone));
    }

    private void inserir() {
        QuartoDao dao = HotelMontainDatabase.getInstance(this).quartoDao();

        try {
            dao.inserir(quarto());

            new AlertDialog.Builder(this)
                    .setMessage("Quarto cadastrado com sucesso")
                    .setNeutralButton("Ok", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();

        } catch (Exception e) {
            Log.e("salvar", e.getMessage());
            AlertUtil.showSucesso(this, "Erro ao tentar inserir quarto !!!");
        }
    }

    private void atualizar() {
        QuartoDao dao = HotelMontainDatabase.getInstance(this).quartoDao();

        try {
            mQuarto.numCamas = getInt(etNumCamas);
            mQuarto.numTvs = getInt(etNumTvs);
            mQuarto.andar = getInt(etAndar);
            mQuarto.numBanheiros = getInt(etNumBanheiros);
            mQuarto.numQuarto = getInt(etNumQuarto);
            mQuarto.numTelefone = getInt(etTelefone);
            mQuarto.status = getInt(etStatus);
            mQuarto.valor = getDouble(etValor);

            dao.atualizar(mQuarto.id, mQuarto.numCamas, mQuarto.numTvs, mQuarto.andar,
                    mQuarto.numBanheiros, mQuarto.numQuarto, mQuarto.numTelefone, mQuarto.status, mQuarto.valor);

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

    private Quarto quarto() {
        Quarto quarto = new Quarto();

        quarto.numCamas = getInt(etNumCamas);
        quarto.numTvs = getInt(etNumTvs);
        quarto.andar = getInt(etAndar);
        quarto.numBanheiros = getInt(etNumBanheiros);
        quarto.numQuarto = getInt(etNumQuarto);
        quarto.numTelefone = getInt(etTelefone);
        quarto.status = getInt(etStatus);
        quarto.valor = getDouble(etValor);

        return quarto;
    }

//    private boolean validate() {
//        if (isEmpty(etNumCamas)) {
//
//        }
//    }

}