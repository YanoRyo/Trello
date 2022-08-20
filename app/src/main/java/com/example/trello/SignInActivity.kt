package com.example.trello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setInActionBar()
    }
    private fun setInActionBar(){
        val tbSignInBar: Toolbar = findViewById(R.id.toolbar_sign_in_activity)
        setSupportActionBar(tbSignInBar)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        tbSignInBar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}