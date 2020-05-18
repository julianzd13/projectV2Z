package com.example.projectV3S.Room

import android.app.Application
import androidx.room.Room

class NewResRoom : Application() {

    companion object{
        lateinit var database1: NewresDataBase
        lateinit var database2: LocalresDataBase
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

        database2 = Room.databaseBuilder(
            this,
            LocalresDataBase::class.java,
            "localres_DB"
        )
            .allowMainThreadQueries()
            .build()


    }

}