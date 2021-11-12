package com.example.hotelmontain.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FuncionarioDao {

    @Insert
    void inserir(Funcionario funcionario);

    @Query("Select * from Funcionario where cpf = :cpf")
    Funcionario buscarPeloCpf(String cpf);

}