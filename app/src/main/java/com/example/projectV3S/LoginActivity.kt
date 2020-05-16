package com.example.projectV3S

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projectV3S.UTILS.Constantes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var googleSignInClient : GoogleSignInClient? = null
    val RC_SIGN_IN = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser

        if (user?.uid != null){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)


        btn_login_google.setOnClickListener {
            var signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
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

            if (correo.isNotEmpty() && contras.isNotEmpty()) {

                auth.signInWithEmailAndPassword(correo, contras)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG001", "signInWithEmail:success")

                            //Toast.makeText(this, "BienPueda", Toast.LENGTH_SHORT).show()
                            intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()


                            //val user = auth.currentUser
                            //updateUI(user)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG002", "signInWithEmail:failure", task.exception)
                            if (task.exception!!.message.equals(getString(R.string.error_msg_login))) {
                                Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
                            }else {
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show() }
                            et_mail.setText(Constantes.EMPTY)
                            et_password.setText(Constantes.EMPTY)
                            //updateUI(null)
                        }

                        // ...
                    }

            }else Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show()

        }


    }


    fun firebaseAuthWithGoogle(acct : GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(acct?.idToken,null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1234 && resultCode == Activity.RESULT_CANCELED)
            Toast.makeText(this, "No Registro", Toast.LENGTH_SHORT).show()

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
           // Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        }


        if(requestCode == RC_SIGN_IN){
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            var account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



}




