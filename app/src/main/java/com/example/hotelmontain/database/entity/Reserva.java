package com.example.hotelmontain.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Reserva implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int numReserva;
    public int numQuarto;
    public int numHospedes;
    public String dataHorario;
}