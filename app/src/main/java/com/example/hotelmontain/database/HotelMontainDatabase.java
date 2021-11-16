package com.example.hotelmontain.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Funcionario.class}, version = 1)
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
}
