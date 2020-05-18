package com.example.myfirebaseauthenticationoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.mEdtMail
import kotlinx.android.synthetic.main.activity_registration.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginbtn = findViewById<View>(R.id.mBtnLogin) as Button
        val regbtn = findViewById<View>(R.id.mBtnRegister) as Button


        loginbtn.setOnClickListener(View.OnClickListener {
                View -> login()
        })

        regbtn.setOnClickListener(View.OnClickListener {
            View -> reigister()
        })
    }

    private fun login(){
        val emailTxt = findViewById<View>(R.id.mEdtMail) as EditText
        val passwordTxt = findViewById<View>(R.id.mEdtPassword) as EditText

        var email = mEdtMail.text.toString()
        var password = mEdtPassword.text.toString()

        if (email.isEmpty() or password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,TimelineActivity ::class.java))
                        Toast.makeText(this,"Logged In Successfully",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Error !(:",Toast.LENGTH_LONG).show()
                    }
                })

        }else{
            Toast.makeText(this,"Please fill out all the credentials",Toast.LENGTH_LONG).show()
        }
    }
    private fun reigister(){
        startActivity(Intent(this, RegistrationActivity ::class.java))
    }
}


