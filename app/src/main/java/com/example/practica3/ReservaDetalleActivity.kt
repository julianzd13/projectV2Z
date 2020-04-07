package com.example.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.content_reserva_detalle.*

class ReservaDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_detalle)
        //setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservas:Reservas =intent?.getSerializableExtra("reserva")as Reservas
        tv_cancha.text =reservas.cancha
       // tv_fecha.text=reservas.fecha

    }
}
