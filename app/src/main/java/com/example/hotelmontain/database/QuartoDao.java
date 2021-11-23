package com.example.hotelmontain.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuartoDao {

    @Insert
    void inserir(Quarto quarto);

    @Query("Select * from Quarto")
    List<Quarto> getQuartos();
}
