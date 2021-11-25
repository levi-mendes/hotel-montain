package com.example.hotelmontain.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reserva {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int numReserva;
    public int numQuarto;
    public int numHospedes;
    public String dataHorario;
}