package com.example.projectV3S.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectV3S.model.ReservasLocalRoom

@Database(entities = [ReservasLocalRoom::class], version = 1)

    abstract class LocalresDataBase : RoomDatabase(){

    abstract fun LocalResDAO(): LocalResDAO

}