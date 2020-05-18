package com.example.myfirebaseauthenticationoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RegistrationActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

      val  regBtn = findViewById<View>(R.id.mBtnRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        regBtn.setOnClickListener(View.OnClickListener {
             View -> register()
        })
    }

    private fun register(){
        val emailTxt = findViewById<View>(R.id.mEdtMail) as EditText
        val passwordTxt = findViewById<View>(R.id.mEdtPassword) as EditText
        val nameTxt = findViewById<View>(R.id.mEdtName) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if (!email.isEmpty() or password.isEmpty() or name.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        val user = mAuth.currentUser
                        val uid = user!!.uid
                        mDatabase.child(uid).child("Names").setValue(name)
                        Toast.makeText(this,"Successfully Signed In",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Error !(:",Toast.LENGTH_LONG).show()
                    }

                })
        }else{
            Toast.makeText(this,"Please fill all the credentials",Toast.LENGTH_LONG).show()
        }
    }
}
