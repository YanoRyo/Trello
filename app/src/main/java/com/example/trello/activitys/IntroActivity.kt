package com.example.trello.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.trello.R

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnSignUP: Button = findViewById(R.id.btn_sign_up_intro)
        val btnSignIn: Button = findViewById(R.id.btn_sign_in_intro)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        btnSignUP.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


    }
}