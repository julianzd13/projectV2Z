package com.example.practic2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.practic2.UTILS.Constantes
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var e_mails : String = Constantes.EMPTY
    var passs: String = Constantes.EMPTY
    var e_mailu : String = Constantes.EMPTY
    var passu: String = Constantes.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var datosRecibidos = intent.extras
        if (datosRecibidos != null) {

             e_mails = datosRecibidos?.getString("e_mail").toString()
             passs = datosRecibidos?.getString("pass").toString()


        }


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



            if (correo == e_mailu && contras == passu && correo.isNotEmpty() && passu.isNotEmpty()){
                intent = Intent(this, DrawerActivity::class.java)
                intent.putExtra("e_mail",correo)
                intent.putExtra("pass", contras)
                startActivity(intent)
                finish()
               et_password.setText("")
               et_mail.setText("")

            }else if (correo == e_mails && contras == passs && correo.isNotEmpty() && passs.isNotEmpty()){
                intent = Intent(this, DrawerActivity::class.java)
                intent.putExtra("e_mail",correo)
                intent.putExtra("pass", correo)
                startActivity(intent)
                finish()
               et_password.setText("")
               et_mail.setText("")

            }else if (correo.isEmpty() || e_mailu.isNotEmpty() && correo != e_mailu || e_mails.isNotEmpty() && correo != e_mails){
                Toast.makeText(this, "Error E-Mail", Toast.LENGTH_SHORT).show()

            }else if (contras.isEmpty() || passu.isNotEmpty() && contras != passu || passs.isNotEmpty() && contras != passs){
                Toast.makeText(this, "Error PASSWORD", Toast.LENGTH_SHORT).show()

            }else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()


/*

            if (correo.isEmpty() || contras.isEmpty()){
                Toast.makeText(this, getText(R.string.error_login), Toast.LENGTH_SHORT).show()


            }

            else
                if (correo == e_mail && contras == pass) {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("e_mail", e_mail)
                    intent.putExtra("pass", pass)
                    //startActivity(intent)
                    startActivityForResult(intent, 1234)
                    finish()




                    et_password.setText("")
                    et_mail.setText("")




                } else {

                    if (correo != e_mail) {
                        Toast.makeText(this, getText(R.string.error_correo), Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (contras != pass) {
                        Toast.makeText(this, getText(R.string.error_passw), Toast.LENGTH_SHORT)
                            .show()
                    }
                }*/

        }






    }








    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1234 && resultCode == Activity.RESULT_CANCELED)
            Toast.makeText(this, "No envio nada", Toast.LENGTH_SHORT).show()
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
           // Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()

            var datosrecibidos1 = data?.extras

                 e_mailu= datosrecibidos1?.getString("e_mail").toString()


                 passu = datosrecibidos1?.getString("pass").toString()

            Log.d("e_ma", e_mailu)
            Log.d("pas", passu)






            }
        super.onActivityResult(requestCode, resultCode, data)




        }
    }




