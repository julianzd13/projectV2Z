package com.example.practica3

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast

import androidx.annotation.RequiresApi
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_new_reserva_step1.*

class NewReservaStep1 : AppCompatActivity() {

    val displaylist: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reserva_step1)

        searchView.isIconifiedByDefault = false

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("escenarios")


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                firebaseSearchSub(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //firebaseSearchChan(newText)
                /*if (newText!!.isNotEmpty()){
                    displaylist.clear()
                    val search = newText.toLowerCase()
                }else{
                    displaylist.clear()
                    myRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (snapshot in dataSnapshot.children) {
                                val escenarion = snapshot.getValue(Escenario::class.java)
                                Log.d("quepasobien", "Value is: ${escenarion?.nombre}")
                                displaylist.add(escenarion?.nombre.toString())
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Failed to read value
                            Log.w("quepasomal", "Failed to read value.", error.toException())
                        }
                    })
                }*/
                return false
            }

        })


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snapshot in dataSnapshot.children) {
                    val usuarion = snapshot.getValue(Usuario::class.java)
                    Log.d("quepasobien", "Value is: ${usuarion?.nombre}")
                    textView3.text = usuarion?.nombre + usuarion?.id
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })












    }

    private fun firebaseSearchSub(searchText: String?) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("escenarios")

        var existecancha = false

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val canchan = snapshot.getValue(Escenario::class.java)
                    if (canchan!!.nombre.equals(searchText)) {
                        //val canchaselec = usuarion.telefono?.let { myRef.child(it) }
                        val canchaselec = canchan.facilitador
                        textView3.text = canchaselec.toString()
                        existecancha = true
                    }
                }
                if (!existecancha) {
                    Toast.makeText(this@NewReservaStep1, "No existe", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })

    }

    private fun firebaseSearchChan(searchText: String?) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("escenarios")

        var existecancha = false

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val canchan = snapshot.getValue(Escenario::class.java)
                    if (canchan!!.nombre.equals(searchText)) {
                        //val canchaselec = usuarion.telefono?.let { myRef.child(it) }
                        val canchaselec = canchan.facilitador
                        textView3.text = canchaselec.toString()
                        existecancha = true
                    }
                }
                if (!existecancha) {
                    Toast.makeText(this@NewReservaStep1, "No existe", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })

    }



}
