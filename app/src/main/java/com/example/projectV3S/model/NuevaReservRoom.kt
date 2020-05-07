package com.example.projectV3S.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_newres")

class NuevaReservRoom (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "escenario") val escen: String?,
    @ColumnInfo(name = "fecha") val fecha: String?,
    @ColumnInfo(name = "hora") val hora: String?,
    @ColumnInfo(name = "participante1") val participante1: String?= "",
    @ColumnInfo(name = "participante2") val participante2: String?= "",
    @ColumnInfo(name = "participante3") val participante3: String?= "",
    @ColumnInfo(name = "participante4") val participante4: String?= "",
    @ColumnInfo(name = "participante5") val participante5: String?= "",
    @ColumnInfo(name = "participante6") val participante6: String?= "",
    @ColumnInfo(name = "participante7") val participante7: String?= "",
    @ColumnInfo(name = "participante8") val participante8: String?= "",
    @ColumnInfo(name = "participante9") val participante9: String?= "",
    @ColumnInfo(name = "participante10") val participante10: String?= "",
    @ColumnInfo(name = "participante11") val participante11: String?= "",
    @ColumnInfo(name = "participante12") val participante12: String?= "",
    @ColumnInfo(name = "participante13") val participante13: String?= "",
    @ColumnInfo(name = "participante14") val participante14: String?= "",
    @ColumnInfo(name = "participante15") val participante15: String?= "",
    @ColumnInfo(name = "participante16") val participante16: String?= ""
)