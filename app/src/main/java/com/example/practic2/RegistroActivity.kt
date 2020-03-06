package com.example.practic2

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import com.example.practic2.UTILS.Constantes
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
   private lateinit var fecha : String
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
                cal.set(Calendar.DAY_OF_YEAR, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha =sdf.format(cal.time).toString()
                Log.d("Fecha",fecha)
                //date = sdf.format(cal.time).toString()

            }

        }




        bt_save.setOnClickListener {
            val name = et_user_name.text.toString()
            val e_mail = et_user_email.text.toString()
            val phon = et_user_phon.text.toString()
            val pass = et_user_pass.text.toString()
            val repass = et_user_rpass.text.toString()
            var hobbies = Constantes.EMPTY

            if (cb_movies.isChecked) hobbies =
                hobbies + Constantes.SPACE + getString(R.string.movies)
            if (cb_gym.isChecked) hobbies = hobbies + Constantes.SPACE + getString(R.string.gym)
            if (cb_read.isChecked) hobbies = hobbies + Constantes.SPACE + getString(R.string.read)
            if (cb_shopping.isChecked) hobbies =
                hobbies + Constantes.SPACE + getString(R.string.shopping)

            if (name.isEmpty() || e_mail.isEmpty() || phon.isEmpty() ||
                pass.isEmpty() || repass.isEmpty() || hobbies.isEmpty()
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


                        tv_result.text =
                            getString(R.string.user_name) + Constantes.SPACE + name + Constantes.INTERLINE + getString(
                                R.string.user_email
                            ) + Constantes.SPACE + e_mail + Constantes.INTERLINE + getString(R.string.user_phon) + Constantes.SPACE + phon +
                                    Constantes.INTERLINE + getString(R.string.hobbies) + Constantes.SPACE + hobbies + Constantes.INTERLINE
                        var intent = Intent(this, LoginActivity::class.java)
                        intent.putExtra("e_mail", e_mail)
                        intent.putExtra("pass", pass)
                        setResult(Activity.RESULT_OK, intent)
                       // startActivity(intent)
                        finish()


                    }
                }

            }
        }




    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }
}
