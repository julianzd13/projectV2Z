package com.example.projectV3S

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.projectV3S.UTILS.Constantes
import com.example.projectV3S.model.Usuario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_new_reserva_step2.*



class NewReservaStep2 : AppCompatActivity() {

    var num_particif1 : String = Constantes.EMPTY
    var numpartici = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reserva_step2)

        var dataf1 = intent?.extras
        num_particif1 = dataf1!!.getString("Numpartici").toString()

        tv_partici_step2.text = "El numero de Praticipantes es " + num_particif1

        SetearSpinnersyeditt()

        bt_conti_to_step3.setOnClickListener {
            numpartici = 0
            var oknumpartici = Comprobacionlayers()
            if (oknumpartici == num_particif1.toInt()){
                Toast.makeText(this, "BIIIIENNN", Toast.LENGTH_SHORT).show()
            }
        }


    }

    @SuppressLint("ResourceType")
    private fun SetearSpinnersyeditt() {
        sp_cedula_step2_1.id = 1
        sp_cedula_step2_2.id = 2
        sp_cedula_step2_3.id = 3
        sp_cedula_step2_4.id = 4
        sp_cedula_step2_5.id = 5
        sp_cedula_step2_6.id = 6
        sp_cedula_step2_7.id = 7
        sp_cedula_step2_8.id = 8
        sp_cedula_step2_9.id = 9
        sp_cedula_step2_10.id = 10
        sp_cedula_step2_11.id = 11
        sp_cedula_step2_12.id = 12
        sp_cedula_step2_13.id = 13
        sp_cedula_step2_14.id = 14
        sp_cedula_step2_15.id = 15
        sp_cedula_step2_16.id = 16
        for (i in 1..num_particif1.toInt()){
            //var keystring1 = "sp_cedula_step2_" + i.toString()
            //sp_cedula_step2_${i}.visibility = View.VISIBLE
            //keystring1.visibility = View.VISIBLE
            findViewById<Spinner>(i).visibility = View.VISIBLE
        }

        et_user_num_cedu_step2_1.id = 21
        et_user_num_cedu_step2_2.id = 22
        et_user_num_cedu_step2_3.id = 23
        et_user_num_cedu_step2_4.id = 24
        et_user_num_cedu_step2_5.id = 25
        et_user_num_cedu_step2_6.id = 26
        et_user_num_cedu_step2_7.id = 27
        et_user_num_cedu_step2_8.id = 28
        et_user_num_cedu_step2_9.id = 29
        et_user_num_cedu_step2_10.id = 30
        et_user_num_cedu_step2_11.id = 31
        et_user_num_cedu_step2_12.id = 32
        et_user_num_cedu_step2_13.id = 33
        et_user_num_cedu_step2_14.id = 34
        et_user_num_cedu_step2_15.id = 35
        et_user_num_cedu_step2_16.id = 36
        for (j in 21..(20 + num_particif1.toInt())){
            //var keystring1 = "sp_cedula_step2_" + i.toString()
            //sp_cedula_step2_${i}.visibility = View.VISIBLE
            //keystring1.visibility = View.VISIBLE
            findViewById<EditText>(j).visibility = View.VISIBLE
        }
    }

    @SuppressLint("ResourceType")
    private fun Comprobacionlayers(): Int {

        et_user_num_cedu_step2_1.id = 21
        et_user_num_cedu_step2_2.id = 22
        et_user_num_cedu_step2_3.id = 23
        et_user_num_cedu_step2_4.id = 24
        et_user_num_cedu_step2_5.id = 25
        et_user_num_cedu_step2_6.id = 26
        et_user_num_cedu_step2_7.id = 27
        et_user_num_cedu_step2_8.id = 28
        et_user_num_cedu_step2_9.id = 29
        et_user_num_cedu_step2_10.id = 30
        et_user_num_cedu_step2_11.id = 31
        et_user_num_cedu_step2_12.id = 32
        et_user_num_cedu_step2_13.id = 33
        et_user_num_cedu_step2_14.id = 34
        et_user_num_cedu_step2_15.id = 35
        et_user_num_cedu_step2_16.id = 36


        for (j in 21..(20 + num_particif1.toInt())){

            var vari1 : String= findViewById<EditText>(j).text.toString()
            var vari2 : String
            var flag = 0
            if (vari1.isNotEmpty()){
                for (k in (j + 1)..(20 + num_particif1.toInt())){
                    vari2 = findViewById<EditText>(k).text.toString()
                    if(vari2.isNotEmpty()){
                        if (vari1 == vari2){
                            Toast.makeText(this, getText(R.string.error_step2), Toast.LENGTH_SHORT).show()
                            findViewById<EditText>(k).setText(Constantes.EMPTY)
                            flag = 1
                        }

                    }
                }
                if(flag!= 1){
                    var checkvar = verificacionfireb(vari1)
                    if (checkvar == false) findViewById<EditText>(j).setText(Constantes.EMPTY)
                    if (checkvar == true) numpartici +=1
                }
            }
        }

        return numpartici
    }

    private fun verificacionfireb(searchText: String?): Boolean {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")
        var existedocu = false

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children){
                    val usertodocu = snapshot.getValue(Usuario::class.java)
                    if (usertodocu!!.num_documento.equals(searchText)){
                        existedocu = true

                    }
                }
                if (!existedocu){
                    Toast.makeText(this@NewReservaStep2, "NO existe Ususario", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("quepasomal", "Failed to read value", error.toException())
            }
        })
        return existedocu
    }


}
