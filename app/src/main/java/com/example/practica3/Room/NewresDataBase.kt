package com.example.practica3.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practica3.model.NuevaReservRoom

@Database(entities = [NuevaReservRoom::class], version= 1)

    abstract class NewresDataBase : RoomDatabase() {

        abstract fun NewresDAO(): NewresDAO

    }