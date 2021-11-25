package com.example.hotelmontain.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Hospede implements Serializable {

    public String nome;
    public String email;
    public String dataNascimento;
    @NonNull
    @PrimaryKey
    public String cpf;
    public int sexo;
    public String estadoCivil;
    public String cep;
    public String telefone;
    public String endereco;
    public String numero;
    public String cidade;
    public String estado;
}
