package com.example.projectV2Z

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectV2Z.UTILS.Constantes
import com.example.projectV2Z.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*


class RegistroActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private var fecha : String = Constantes.EMPTY
   // private lateinit var date : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var sexo: String = getString(R.string.male)

        rb_male.setOnClickListener {
            sexo = getString(R.string.male)
        }

        rb_female.setOnClickListener {
            sexo = getString(R.string.female)
        }

        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha =sdf.format(cal.time).toString()

                et_user_fecha_naci.setText(fecha)
                //Log.d("Fecha",fecha)
                //date = sdf.format(cal.time).toString()

            }

        }

        et_user_fecha_naci.setOnClickListener {

            DatePickerDialog(
                this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)

            ).show()

        }


        bt_save.setOnClickListener {

            val tipo_docu = sp_cedula.selectedItem.toString()
            val num_docu = et_user_num_cedu.text.toString()
            val name = et_user_name.text.toString()
            val lname = et_user_lname.text.toString()
            val e_mail = et_user_email.text.toString()
            val phon = et_user_phon.text.toString()
            val pass = et_user_pass.text.toString()
            val repass = et_user_rpass.text.toString()
            val municipio = sp_city.selectedItem.toString()



            if (tipo_docu.isEmpty() || tipo_docu == "Seleccionar Documento" || num_docu.isEmpty() ||name.isEmpty() || lname.isEmpty() || e_mail.isEmpty() ||
                pass.isEmpty() || repass.isEmpty() || municipio.isEmpty() || municipio == "Municipo" || fecha == Constantes.EMPTY
            ) {
                Toast.makeText(this, getText(R.string.error_login), Toast.LENGTH_SHORT).show()
            } else {

                if (pass.length < 6) {

                    Toast.makeText(this, getText(R.string.error_tama), Toast.LENGTH_SHORT).show()

                } else {

                    if (pass != repass
                    ) {
                        Toast.makeText(this, getText(R.string.error_pass), Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        progressBar3.visibility = View.VISIBLE

                        val auth: FirebaseAuth
                        auth = FirebaseAuth.getInstance()

                        auth.createUserWithEmailAndPassword(e_mail, pass)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {

                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success")
                                    val user = auth.currentUser
                                    //updateUI(user)

                                    writeNewUser(user!!.uid, name, lname, tipo_docu, num_docu, e_mail,
                                        sexo, fecha, phon, municipio)



                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                    progressBar3.visibility = View.GONE
                                    Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                    //updateUI(null)
                                }

                                // ...
                            }


                        //var intent = Intent(this, LoginActivity::class.java)
                        //setResult(Activity.RESULT_OK, intent)
                        //startActivity(intent)
                        //finish()

                    }
                }

            }
        }


    }



    private fun writeNewUser(userId: String, a: String, b:String, c: String?, d: String?,
                             e: String?, f: String?, g: String?, h: String?, i: String?) {

        //var database: DatabaseReference // ...
        //database = FirebaseDatabase.getInstance().reference
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")

        val user = Usuario(
            id = userId, nombre = a, apellido = b, tipo_documento = c, num_documento = d,
            email = e, sexo = f, fecha_nacimiento = g, telefono = h, municipio = i
        )


        myRef.child(userId).setValue(user).addOnSuccessListener {
            var intent = Intent(this, LoginActivity::class.java)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }




}
