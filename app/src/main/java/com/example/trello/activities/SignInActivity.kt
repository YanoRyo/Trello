package com.example.trello.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.trello.R
import com.example.trello.models.User
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {

    private var email: TextView? = null
    private var password: TextView? = null
    private var signInButton: Button? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setInActionBar()
    }

    private fun setInActionBar(){
        val tbSignInBar: Toolbar = findViewById(R.id.toolbar_sign_in_activity)
        setSupportActionBar(tbSignInBar)
        signInButton = findViewById(R.id.btn_sign_in)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        tbSignInBar.setNavigationOnClickListener{
            onBackPressed()
        }
        signInButton?.setOnClickListener {
            sighInRegisteredUser()
        }
    }

    private fun sighInRegisteredUser(){
        email = findViewById(R.id.et_email_sign_in)
        password = findViewById(R.id.et_password_sign_in)

        if (validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            auth.signInWithEmailAndPassword(email?.text.toString(), password?.text.toString())
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Sigh in" , "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sigh in", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }

    }

    fun signInSuccess(user: User){
        hideProgressDialog()
        startActivity(Intent(this,IntroActivity::class.java))
        finish()
    }

    private fun validateForm(email: TextView?, password: TextView?): Boolean {
        if(email?.text.toString().isEmpty()){
            Toast.makeText(this,"Your email is blank", Toast.LENGTH_LONG).show()
            return false
        }else if (password?.text.toString().isEmpty()){
            Toast.makeText(this,"Your password is blank", Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
    }
}