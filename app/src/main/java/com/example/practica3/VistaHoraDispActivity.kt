package com.example.practica3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.UTILS.Constantes
import com.example.practica3.model.Reservas
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_vista_hora_disp.*

class VistaHoraDispActivity : AppCompatActivity() {

    var fecha_new : String? = Constantes.EMPTY
    var cancha_new : String? = Constantes.EMPTY

    val horariosdispolist : MutableList<Reservas> = mutableListOf()
    //lateinit var horariodispoRVAdapter: HoradispRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_hora_disp)

        var datafnew = intent?.extras
        fecha_new = datafnew?.getString("Fecha")
        cancha_new = datafnew?.getString("Cancha")



        rv_horarios_disp.layoutManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        val horariodispoRVAdapter = HoradispRVAdapter(
            applicationContext,
            horariosdispolist as ArrayList
        )
        rv_horarios_disp.setHasFixedSize(true)
        rv_horarios_disp.adapter = horariodispoRVAdapter


    }

    override fun onResume() {
        super.onResume()
        cargarHorariosDisp()
    }

    private fun cargarHorariosDisp() {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservas").child("escenario").child(cancha_new.toString())

        horariosdispolist.add(Reservas("08:00 - 10:00"))
        horariosdispolist.add(Reservas("10:00 - 12:00"))
        horariosdispolist.add(Reservas("12:00 - 14:00"))
        horariosdispolist.add(Reservas("14:00 - 16:00"))
        horariosdispolist.add(Reservas("16:00 - 18:00"))
        horariosdispolist.add(Reservas("18:00 - 20:00"))
        horariosdispolist.add(Reservas("20:00 - 22:00"))

        var existeFecha = false

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val fecha = snapshot.getValue(Reservas::class.java)
                    if (fecha!!.fecha.equals(fecha_new)) {
                        for (snapshot2 in dataSnapshot.children){
                            val horario = snapshot.getValue(Reservas::class.java)
                            horariosdispolist.remove(horario)
                        }

                        existeFecha = true
                    }
                    if (!existeFecha) {
                        Toast.makeText(this@VistaHoraDispActivity,"oeoeooeoeoeeoeoeo", Toast.LENGTH_SHORT).show()
                    }
                }

                //HoradispRVAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Lista", "Failed to read value.", error.toException())
            }
        })
    }

    /*override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }*/




}
