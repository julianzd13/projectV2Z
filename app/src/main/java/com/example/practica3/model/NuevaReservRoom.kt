package com.example.practica3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_newres")

class NuevaReservRoom (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "escenario") val escen: String?,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "hora") val hora: String
)