package com.example.projectV3S

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.example.projectV3S.UTILS.Constantes
import kotlinx.android.synthetic.main.activity_new_reserva_step2.*



class NewReservaStep2 : AppCompatActivity() {

    var num_particif1 : String = Constantes.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reserva_step2)

        var dataf1 = intent?.extras
        num_particif1 = dataf1!!.getString("Numpartici").toString()

        tv_partici_step2.text = "El numero de Praticipantes es " + num_particif1

        SetearSpinnersyeditt()

        bt_conti_to_step3.setOnClickListener {
            Comprobacionlayers()
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

    private fun Comprobacionlayers() {



    }


}
