package com.example.projectV3S.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectV3S.model.NuevaReservRoom

@Database(entities = [NuevaReservRoom::class], version= 1)

    abstract class NewresDataBase : RoomDatabase() {

        abstract fun NewresDAO(): NewresDAO

    }