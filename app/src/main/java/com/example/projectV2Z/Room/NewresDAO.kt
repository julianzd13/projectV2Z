package com.example.projectV2Z.Room

import androidx.room.*
import com.example.projectV2Z.model.NuevaReservRoom


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