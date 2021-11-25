package com.example.hotelmontain.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotelmontain.database.entity.Hospede;

import java.util.List;

@Dao
public interface HospedeDao {

    @Insert
    void inserir(Hospede Hospede);

    @Query("Select * from Hospede where cpf = :cpf")
    Hospede buscarPeloCpf(String cpf);

    @Query("Select * from Hospede")
    List<Hospede> getHospedes();

    @Delete
    void remover(Hospede hospede);

    @Query("UPDATE Hospede SET nome = :nome, email = :email, dataNascimento = :dataNascimento," +
            "sexo = :sexo, estadoCivil = :estadoCivil, cep = :cep, telefone = :telefone," +
            "endereco = :endereco, numero = :numero, cidade = :cidade, estado = :estado WHERE cpf = :cpf ")
    void atualizar(String nome, String email, String dataNascimento, int sexo,
                   String estadoCivil, String cep, String telefone,
                   String endereco, String numero, String cidade, String estado, String cpf);
}