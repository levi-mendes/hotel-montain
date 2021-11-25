package com.example.hotelmontain.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotelmontain.database.entity.Funcionario;

import java.util.List;

@Dao
public interface FuncionarioDao {

    @Insert
    void inserir(Funcionario funcionario);

    @Query("Select * from Funcionario where cpf = :cpf")
    Funcionario buscarPeloCpf(String cpf);

    @Query("Select * from Funcionario")
    List<Funcionario> getFuncionarios();

    @Delete
    void remover(Funcionario funcionario);

    @Query("UPDATE Funcionario SET nome = :nome, email = :email, dataNascimento = :dataNascimento," +
            "sexo = :sexo, estadoCivil = :estadoCivil, cep = :cep, cargo = :cargo, telefone = :telefone," +
            "endereco = :endereco, numero = :numero, cidade = :cidade, estado = :estado WHERE cpf = :cpf ")
    void atualizar(String nome, String email, String dataNascimento, int sexo,
                   String estadoCivil, String cep, String cargo, String telefone,
                   String endereco, String numero, String cidade, String estado, String cpf);
}