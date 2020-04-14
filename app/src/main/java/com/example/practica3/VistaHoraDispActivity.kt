package com.example.practica3

import android.app.Activity
import android.content.Intent
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

class VistaHoraDispActivity : AppCompatActivity(), HoradispRVAdapter.OnItemClickListener {

    var fecha_new : String? = Constantes.EMPTY
    var cancha_new : String? = Constantes.EMPTY

    var horariosdispolist : MutableList<Reservas> = mutableListOf()
    //lateinit var horariodispoRVAdapter: HoradispRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_hora_disp)

        var datafnew = intent?.extras
        fecha_new = datafnew?.getString("Fecha")
        cancha_new = datafnew?.getString("Cancha")

        Log.d("aaafecha", fecha_new)
        Log.d("ssscancha", cancha_new)



        rv_horarios_disp.layoutManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        /*val horariodispoRVAdapter = HoradispRVAdapter(
            applicationContext,
            horariosdispolist as ArrayList
        )
        rv_horarios_disp.setHasFixedSize(true)
        rv_horarios_disp.adapter = horariodispoRVAdapter*/


    }

    override fun onResume() {
        super.onResume()
        cargarHorariosDisp()
    }

    private fun cargarHorariosDisp() {

        horariosdispolist.clear()
        horariosdispolist.add( Reservas("08:00 - 10:00"))
        horariosdispolist.add( Reservas("10:00 - 12:00"))
        horariosdispolist.add( Reservas("12:00 - 14:00"))
        horariosdispolist.add( Reservas("14:00 - 16:00"))
        horariosdispolist.add( Reservas("16:00 - 18:00"))
        horariosdispolist.add( Reservas("18:00 - 20:00"))
        horariosdispolist.add( Reservas("20:00 - 22:00"))

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservas").child(fecha_new.toString()).child(cancha_new.toString())


        var existeFecha = false

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    var fechan = snapshot.getValue(Reservas::class.java)
                    Log.d("nonon", fechan.toString())
                    if (fechan!!.fecha.equals(fecha_new)) {

                        //Log.d("siiiii", "si diiiooooo")
                        Toast.makeText(this@VistaHoraDispActivity,"ENTRO EN EL IF", Toast.LENGTH_SHORT).show()

                        horariosdispolist.removeAll { it.hora == fechan.hora}

                        existeFecha = true

                    }else if (!existeFecha) {
                        Toast.makeText(this@VistaHoraDispActivity,"NO ENTRO EN EL IF", Toast.LENGTH_SHORT).show()
                    }else {
                        onBackPressed()
                    }
                }

                val horariodispoRVAdapter = HoradispRVAdapter(
                    applicationContext,
                    horariosdispolist as ArrayList,
                    this@VistaHoraDispActivity
                )
                rv_horarios_disp.setHasFixedSize(true)
                rv_horarios_disp.adapter = horariodispoRVAdapter

                //HoradispRVAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Lista", "Failed to read value.", error.toException())
            }
        })
    }


    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }

    override fun onItemClick(horario: Reservas) {

        val hora = horario.hora

        val intent = Intent(this, NewReservaStep1::class.java)
        intent.putExtra("horaselec",hora)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}
