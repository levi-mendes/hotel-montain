package com.example.hotelmontain.database;

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
    public int numQuartos;
    public int numTelefone;
    public int status;
    public double valor;
}

/*

CREATE TB_QUARTO (id int not null primari key,
numCamas int not null,



 */