package com.example.hotelmontain.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotelmontain.database.entity.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {

    @Insert
    void inser(Reserva reserva);

    @Delete
    void remover(Reserva reserva);

    @Query("Select * from Reserva")
    List<Reserva> getReservas();
}