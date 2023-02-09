package com.example.trello.activities

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.trello.R
import com.google.firebase.auth.FirebaseAuth


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 「android.R.color.transparent」を指定することで、ステータスバーを透過させている
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    }
}