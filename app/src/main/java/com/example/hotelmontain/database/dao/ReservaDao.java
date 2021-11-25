package com.example.hotelmontain.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.hotelmontain.database.entity.Reserva;

@Dao
public interface ReservaDao {

    @Insert
    void inser(Reserva reserva);
}