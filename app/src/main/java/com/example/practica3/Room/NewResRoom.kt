package com.example.practica3.Room

import android.app.Application
import androidx.room.Room

class NewResRoom : Application() {

    companion object{
        lateinit var database1: NewresDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database1 = Room.databaseBuilder(
                this,
                NewresDataBase::class.java,
                "nuevares_DB"
            )
            .allowMainThreadQueries()
            .build()

    }

}