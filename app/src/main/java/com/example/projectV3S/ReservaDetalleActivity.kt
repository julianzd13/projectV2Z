package com.example.projectV3S

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.projectV3S.model.Escenario
import com.example.projectV3S.model.Reservas
import com.example.projectV3S.model.ReservasLocal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.content_reserva_detalle.*

class ReservaDetalleActivity : AppCompatActivity() {

    var idreserv: String? = ""
    private lateinit var reservasLocal: ReservasLocal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_detalle)
        //setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reservasLocal = intent?.getSerializableExtra("reserva")as ReservasLocal
        idreserv =reservasLocal.id

        if (reservasLocal.cancha == getString(R.string.Bolera)) imageViewdetail.setImageResource(R.drawable.bolera)
        if (reservasLocal.estado == "Aceptado") bt_certificado.visibility = View.VISIBLE
        if (reservasLocal.estado != "Cancelado" && reservasLocal.estado != "Rechazado") bt_cancelarr.visibility = View.VISIBLE
        tv_cancha.text = reservasLocal.cancha
        tv_estad.text = reservasLocal.estado
        tv_fec.text = reservasLocal.fecha
        tv_hora1.text = reservasLocal.hora

        tv_part1.text = reservasLocal.num_doc_part1
        tv_part2.text = reservasLocal.num_doc_part2
        tv_part3.text = reservasLocal.num_doc_part3
        tv_part4.text = reservasLocal.num_doc_part4
        tv_part5.text = reservasLocal.num_doc_part5
        tv_part6.text = reservasLocal.num_doc_part6
        tv_part7.text = reservasLocal.num_doc_part7
        tv_part8.text = reservasLocal.num_doc_part8
        tv_part9.text = reservasLocal.num_doc_part9
        tv_part10.text = reservasLocal.num_doc_part10
        tv_part11.text = reservasLocal.num_doc_part11
        tv_part12.text = reservasLocal.num_doc_part12
        tv_part13.text = reservasLocal.num_doc_part13
        tv_part14.text = reservasLocal.num_doc_part14
        tv_part15.text = reservasLocal.num_doc_part15
        tv_part16.text = reservasLocal.num_doc_part16

        cargardataffire(reservasLocal.cancha.toString())

        bt_cancelarr.setOnClickListener {
            cancelarreserva()

        }
    }


    private fun cargardataffire(searchText: String) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("escenarios")

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val datoesc = snapshot.getValue(Escenario::class.java)
                    if (datoesc!!.nombre.equals(searchText)) {

                        tv_descrip.text = datoesc.descripcion
                        tv_ubicacion.text = datoesc.ubicacion
                        tv_telefon.text = datoesc.telefono
                        cargarpartici(datoesc.integran!!.toInt())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })

    }

    @SuppressLint("ResourceType")
    private fun cargarpartici(numintegra: Int) {
        ll_doc1.id = 1
        ll_doc2.id = 2
        ll_doc3.id = 3
        ll_doc4.id = 4
        ll_doc5.id = 5
        ll_doc6.id = 6
        ll_doc7.id = 7
        ll_doc8.id = 8
        ll_doc9.id = 9
        ll_doc10.id = 10
        ll_doc11.id = 11
        ll_doc12.id = 12
        ll_doc13.id = 13
        ll_doc14.id = 14
        ll_doc15.id = 15
        ll_doc16.id = 16
        for (i in 1..numintegra){
            findViewById<LinearLayout>(i).visibility = View.VISIBLE
        }
    }

    private fun cancelarreserva() {
        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("reservasuser")
        val childUpdate = HashMap<String, Any>()
        childUpdate["estado"] = "Cancelado"

        myRef.child(user!!.uid).child(idreserv!!).updateChildren(childUpdate).addOnSuccessListener {
            eliminarreserva(user.uid)
        }



    }

    private fun eliminarreserva(userid:String) {
        val database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("reservas").child(reservasLocal.fecha.toString()).child(
            reservasLocal.cancha.toString()
        )

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val reservadel = snapshot.getValue(Reservas::class.java)
                    if (reservadel!!.iduser.equals(userid)) {
                        myRef.child(userid).removeValue().addOnSuccessListener {
                            var intent = Intent(this@ReservaDetalleActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })


    }
}
