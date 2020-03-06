package com.example.practic2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.practic2.UTILS.Constantes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var e_mail : String = Constantes.EMPTY
    var pass: String = Constantes.EMPTY


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datosRecibidos = intent.extras
        if (datosRecibidos != null) {


             e_mail = datosRecibidos?.getString("e_mail").toString()

             pass = datosRecibidos?.getString("pass").toString()










            /*intent.putExtra("e_mail", e_mail)
            intent.putExtra("pass", pass)*/





        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.mcerrar){
            var intent = Intent(this, LoginActivity::class.java)
            //setResult(Activity.RESULT_OK, intent)
            intent.putExtra("e_mail", e_mail)
            intent.putExtra("pass", pass)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

   /* override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }*/





}
