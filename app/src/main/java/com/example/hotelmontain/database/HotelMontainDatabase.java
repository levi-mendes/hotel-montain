package com.example.hotelmontain.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hotelmontain.database.dao.FornecedorDao;
import com.example.hotelmontain.database.dao.FuncionarioDao;
import com.example.hotelmontain.database.dao.QuartoDao;
import com.example.hotelmontain.database.dao.ReservaDao;
import com.example.hotelmontain.database.entity.Fornecedor;
import com.example.hotelmontain.database.entity.Funcionario;
import com.example.hotelmontain.database.entity.Quarto;
import com.example.hotelmontain.database.entity.Reserva;

import java.util.concurrent.Executors;

@Database(entities = {
        Funcionario.class, Quarto.class, Reserva.class,
        Fornecedor.class
},
        version = 8)
public abstract class HotelMontainDatabase extends RoomDatabase {

    private static HotelMontainDatabase mAppDatabase;

    public static HotelMontainDatabase getInstance(Context context) {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(context,
                    HotelMontainDatabase.class, "hotel-montain.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context).quartoDao().inserir(quartos());
                                }
                            });
                        }
                    })
                    .build();
        }

        return mAppDatabase;
    }

    private static Quarto[] quartos() {
        Quarto[] retorno = new Quarto[4];

        retorno[0] = new Quarto(1, 1, 3, 1, 301, 301, 1, 200);
        retorno[1] = new Quarto(3, 2, 2, 1, 210, 210, 0, 420);
        retorno[2] = new Quarto(1, 1, 1, 1, 100, 100, 1, 150);
        retorno[3] = new Quarto(4, 1, 10, 2, 430, 1003, 1, 1500);

        return retorno;
    }

    public abstract FuncionarioDao funcionarioDao();
    public abstract QuartoDao quartoDao();
    public abstract ReservaDao reservaDao();
    public abstract FornecedorDao fornecedorDao();
}