package com.example.projectV3S.Room

import androidx.room.*
import com.example.projectV3S.model.ReservasLocalRoom

@Dao
interface LocalResDAO {

    @Insert
    fun insertLocalresRoom(reservasLocalRoom: ReservasLocalRoom)

    @Query("SELECT * FROM tabla_localres")
    fun getReservaslocalRoom(): List<ReservasLocalRoom>

    @Query("SELECT * FROM tabla_localres")
    fun searchReservalocalRoom(): ReservasLocalRoom

    @Update
    fun updateReservalocalRoom(reservasLocalRoom: ReservasLocalRoom)

    @Delete
    fun deleteReservalocalRoom(reservasLocalRoom: ReservasLocalRoom)
}