package com.example.projectV3S

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectV3S.Room.NewResRoom
import com.example.projectV3S.Room.NewresDAO
import com.example.projectV3S.model.ReservasLocal
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val reservasLocalList: MutableList<ReservasLocal> = ArrayList()
    lateinit var reservasRVAdapter : ReservasRVAdapter

    var googleSignInClient : GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        //eliminar_reserva_enpro()

        cargarreservasuser()


        rv_reservas.layoutManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        /*reservasRVAdapter = ReservasRVAdapter(
            applicationContext,
            reservasLocalList as ArrayList
        )

        rv_reservas.setHasFixedSize(true)
        rv_reservas.adapter = reservasRVAdapter*/



        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG
            //    .setAction("Action", null).show()
            var intent = Intent(this, NewReservaStep1::class.java)
            startActivity(intent)
            //finish()

        }

    }

    private fun cargarreservasuser() {
        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservasuser").child(user!!.uid)

        reservasLocalList.clear()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                reservasLocalList.clear()
                for (snapshot in dataSnapshot.children){
                    val reservalocal = snapshot.getValue(ReservasLocal::class.java)
                    reservasLocalList.add(reservalocal!!)
                }
                reservasRVAdapter = ReservasRVAdapter(
                    applicationContext,
                    reservasLocalList as ArrayList
                )

                rv_reservas.setHasFixedSize(true)
                rv_reservas.adapter = reservasRVAdapter
                //reservasRVAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("Lista","Failed to read value.", error.toException())
            }
        })
    }

    /*
    private fun eliminar_reserva_enpro() {
        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()
        if (nuevaReservRoom != null) {
            newresDAO.deleteReserva(nuevaReservRoom) //Borro Datos de Room
        }
    }
    */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.mcerrar){


            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            FirebaseAuth.getInstance().signOut()
            googleSignInClient?.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        //cargarreservasuser()
        //eliminar_reserva_enpro()
    }



}
