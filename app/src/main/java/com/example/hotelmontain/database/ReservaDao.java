package com.example.hotelmontain.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ReservaDao {

    @Insert
    void inser(Reserva reserva);
}