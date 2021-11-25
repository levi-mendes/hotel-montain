package com.example.hotelmontain.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hotelmontain.database.dao.FuncionarioDao;
import com.example.hotelmontain.database.dao.QuartoDao;
import com.example.hotelmontain.database.dao.ReservaDao;
import com.example.hotelmontain.database.entity.Funcionario;
import com.example.hotelmontain.database.entity.Quarto;
import com.example.hotelmontain.database.entity.Reserva;

@Database(entities = {
        Funcionario.class, Quarto.class, Reserva.class
},
        version = 2)
public abstract class HotelMontainDatabase extends RoomDatabase {

    private static HotelMontainDatabase mAppDatabase;

    public static HotelMontainDatabase getInstance(Context context) {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(context,
                    HotelMontainDatabase.class, "hotel-montain.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return mAppDatabase;
    }

    public abstract FuncionarioDao funcionarioDao();
    public abstract QuartoDao quartoDao();
    public abstract ReservaDao reservaDao();
}
