package com.example.practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.UTILS.Constantes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var reservasList: MutableList<Reservas> = ArrayList()

        reservasList.add(
            Reservas(getString(R.string.reserva1),
                "Aceptado",
                "15/04/2020",
                "14:00-15:00",
                R.drawable.aceptado
            )

        )

        reservasList.add(
            Reservas(getString(R.string.reserva2),
                "Rechazado",
                "11/05/2020",
                "10:00-11:00",
                R.drawable.rechazado
            )

        )

        reservasList.add(
            Reservas(getString(R.string.reserva3),
                "Pendiente",
                "16/07/2020",
                "12:00-13:00",
                R.drawable.pendiente
            )

        )

        reservasList.add(
            Reservas(getString(R.string.reserva4),
                "Cancelado",
                "19/07/2020",
                "7:00-8:00",
                R.drawable.cancelado
            )

        )
        reservasList.add(
            Reservas(getString(R.string.reserva1),
                "Aceptado",
                "19/07/2020",
                "7:00-8:00",
                R.drawable.aceptado
            )

        )

        rv_reservas.setHasFixedSize(true)
        rv_reservas.layoutManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        val reservasRVAdapter = ReservasRVAdapter(
            applicationContext,
            reservasList as ArrayList
        )

        rv_reservas.adapter = reservasRVAdapter



        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.mcerrar){


            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            FirebaseAuth.getInstance().signOut();
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
