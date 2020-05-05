package com.example.projectV3S

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectV3S.model.ReservasLocal
import kotlinx.android.synthetic.main.content_reserva_detalle.*

class ReservaDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_detalle)
        //setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservasLocal: ReservasLocal =intent?.getSerializableExtra("reserva")as ReservasLocal
        tv_cancha.text =reservasLocal.cancha
       // tv_fecha.text=reservas.fecha

    }
}
