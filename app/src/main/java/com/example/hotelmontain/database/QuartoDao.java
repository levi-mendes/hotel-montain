package com.example.hotelmontain.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface QuartoDao {

    @Insert
    void inserir(Quarto quarto);

    @Query("UPDATE Quarto SET numCamas = :numCamas, numTvs = :numTvs, andar = :andar, numBanheiros = :numBanheiros, numQuarto = :numQuarto, numTelefone = :numTelefone, status = :status, valor = :valor WHERE id = :id")
    void atualizar(int id, int numCamas, int numTvs, int andar, int numBanheiros, int numQuarto, int numTelefone, int status, double valor);

    @Query("Select * from Quarto")
    List<Quarto> getQuartos();

    //@Query("DELETE FROM Quarto where id = :id")
    @Delete
    void remover(Quarto quarto);
}
