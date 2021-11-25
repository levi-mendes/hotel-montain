package com.example.hotelmontain.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotelmontain.database.entity.Fornecedor;

import java.util.List;

@Dao
public interface FornecedorDao {

    @Insert
    void inserir(Fornecedor Fornecedor);

//    @Query("Select * from Fornecedor where cnpj = :cnpj")
//    Fornecedor buscarPeloCpf(String cpf);

    @Query("Select * from Fornecedor")
    List<Fornecedor> getFornecedores();

    @Delete
    void remover(Fornecedor Fornecedor);

    @Query("UPDATE Fornecedor SET nome = :nome, email = :email, dataNascimento = :dataNascimento," +
            "sexo = :sexo, estadoCivil = :estadoCivil, cep = :cep, telefone = :telefone," +
            "endereco = :endereco, numero = :numero, cidade = :cidade, estado = :estado " +
            "WHERE cnpj = :cnpj ")
    void atualizar(String nome, String email, String dataNascimento, int sexo,
                   String estadoCivil, String cep, String telefone,
                   String endereco, String numero, String cidade, String estado, String cnpj);
}