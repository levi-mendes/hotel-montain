package com.example.hotelmontain.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotelmontain.database.entity.Funcionario;

@Dao
public interface FuncionarioDao {

    @Insert
    void inserir(Funcionario funcionario);

    @Query("Select * from Funcionario where cpf = :cpf")
    Funcionario buscarPeloCpf(String cpf);

}