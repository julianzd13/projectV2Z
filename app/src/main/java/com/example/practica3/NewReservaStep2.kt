package com.example.practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica3.Room.NewResRoom
import com.example.practica3.Room.NewresDAO
import kotlinx.android.synthetic.main.activity_new_reserva_step2.*
import java.util.*
import kotlin.concurrent.timerTask

class NewReservaStep2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reserva_step2)

        val timer = Timer()
        timer.schedule(timerTask {
            goToMainActivity()
        }, 3000)

        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()

        tv_escen.text = nuevaReservRoom.escen
        tv_fech.text = nuevaReservRoom.fecha
        tv_hor.text = nuevaReservRoom.hora

    }

    private fun goToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()
        newresDAO.deleteReserva(nuevaReservRoom) //Borro Datos de Room
        finish()
    }





}
