package com.example.trello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setUpActionBar()
    }

    private fun setUpActionBar(){
        val tbSignUpBar: Toolbar = findViewById(R.id.toolbar_sign_up_activity)
        setSupportActionBar(tbSignUpBar)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        tbSignUpBar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}