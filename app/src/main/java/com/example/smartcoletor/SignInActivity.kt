package com.example.smartcoletor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SignInActivity : AppCompatActivity() {

    lateinit var btSignIn:Button
    lateinit var btSignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btSignIn = findViewById(R.id.btn_signin)
        btSignUp = findViewById(R.id.btn_signup)

        btSignIn.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })

        btSignUp.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this, SubscribeActivity::class.java)
            startActivity(intent)
        })
    }
}