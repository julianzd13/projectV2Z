package com.example.practica3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.practica3.UTILS.Constantes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()

        btn_registro.setOnClickListener {

            var intent = Intent(this, RegistroActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, 1234)
            et_password.setText("")
            et_mail.setText("")

        }

        btn_login.setOnClickListener {

            var correo = et_mail.text.toString()
            var contras = et_password.text.toString()

            if (correo.isNotEmpty() && contras.isNotEmpty()) {

                auth.signInWithEmailAndPassword(correo, contras)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG001", "signInWithEmail:success")

                            Toast.makeText(this, "BienPueda", Toast.LENGTH_SHORT).show()
                            intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()


                            //val user = auth.currentUser
                            //updateUI(user)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG002", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            et_mail.setText(Constantes.EMPTY)
                            et_password.setText(Constantes.EMPTY)
                            //updateUI(null)
                        }

                        // ...
                    }

            }else Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show()

        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1234 && resultCode == Activity.RESULT_CANCELED)
            Toast.makeText(this, "No Registro", Toast.LENGTH_SHORT).show()

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
           // Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        }

        super.onActivityResult(requestCode, resultCode, data)

    }


}




