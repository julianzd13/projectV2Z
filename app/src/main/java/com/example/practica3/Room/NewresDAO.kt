package com.example.practica3.Room

import androidx.room.*
import com.example.practica3.model.NuevaReservRoom


@Dao
interface NewresDAO {

    @Insert
    fun insertNuevares(nuevareservroom: NuevaReservRoom)

    @Query("SELECT * FROM tabla_newres")
    fun getReservas() : List<NuevaReservRoom>

    @Query("SELECT * FROM tabla_newres ")
    fun searchEscenari(): NuevaReservRoom

    @Delete
    fun deleteReserva(nuevareservroom: NuevaReservRoom)
}