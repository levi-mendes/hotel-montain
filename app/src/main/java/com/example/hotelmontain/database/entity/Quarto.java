package com.example.hotelmontain.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Quarto implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int numCamas;
    public int numTvs;
    public int andar;
    public int numBanheiros;
    public int numQuarto;
    public int numTelefone;
    public int status;
    public double valor;

    public Quarto(int numCamas, int numTvs, int andar, int numBanheiros,
                  int numQuarto, int numTelefone, int status, double valor) {

        this.numCamas = numCamas;
        this.numTvs = numTvs;
        this.andar = andar;
        this.numBanheiros = numBanheiros;
        this.numQuarto = numQuarto;
        this.numTelefone = numTelefone;
        this.status = status;
        this.valor = valor;
    }

    public Quarto() {}
}