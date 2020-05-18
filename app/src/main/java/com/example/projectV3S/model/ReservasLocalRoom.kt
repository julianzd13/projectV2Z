package com.example.projectV3S.model



import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tabla_localres")
class ReservasLocalRoom(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "idreserva") val idreserva: String?,
    @ColumnInfo(name = "escenario") val escen: String?,
    @ColumnInfo(name = "fecha") val fecha: String?,
    @ColumnInfo(name = "hora") val hora: String?,
    @ColumnInfo(name = "estado") val estado: String?,
    @ColumnInfo(name = "descripcion") val descripcion: String?,
    @ColumnInfo(name = "ubicacion") val ubicacion: String?,
    @ColumnInfo(name = "latitud") val latitud: String?,
    @ColumnInfo(name = "longitud") val longitud: String?,
    @ColumnInfo(name = "telefono") val telefono: String?,
    @ColumnInfo(name = "urlimage") val urlimage: String?="",
    @ColumnInfo(name = "integrantes") val integrantes: String?,
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
    ): Serializable
