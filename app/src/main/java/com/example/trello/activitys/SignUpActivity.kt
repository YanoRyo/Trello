package com.example.trello.activitys

import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.trello.R

class SignUpActivity : BaseActivity() {

    private var name: TextView? = null
    private var email: TextView? = null
    private var password: TextView? = null
    private var trimName: TextView? = null
    private var trimEmail: TextView? = null
    private var trimPassword: TextView? = null
    private var signUpButton: Button? = null


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
        signUpButton = findViewById(R.id.btn_sign_up)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        tbSignUpBar.setNavigationOnClickListener{
            onBackPressed()
        }
        signUpButton?.setOnClickListener {
            registerUser()
        }


    }

    private fun registerUser(){

        name = findViewById(R.id.et_name)
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)


        if (name?.getText().toString().length >= 0) {
            val trimName = name?.getText().toString().trim { it <= ' ' }
        } else {
            name?.setError("Plz enter name")
        }
        if (email?.getText().toString().length >= 0) {
            val trimEmail = email?.getText().toString().trim { it <= ' ' }
        } else {
            email?.setError("Plz enter email")
        }
        if (password?.getText().toString().length >= 0) {
            val trimPassword = password?.getText().toString().trim { it <= ' ' }
        } else {
            password?.setError("Plz enter password")
        }

//        name = name?.getText().toString().trim() { it <= ' '}
//        email = email?.text.toString().trim() { it <= ' '}
//        password = password.text.toString().trim() { it <= ' '}

        if(validateForm(trimName,  trimEmail, trimPassword)){
            Toast.makeText(this@SignUpActivity,"Now we can register a new user",Toast.LENGTH_LONG).show()
        }

    }
    private fun validateForm(trimName: TextView?, trimEmail: TextView?, trimPassword: TextView?): Boolean {
        if (trimName?.text.toString().isEmpty()){
            Toast.makeText(this,"Your name is blank",Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
        if (trimEmail?.text.toString().isEmpty()){
            Toast.makeText(this,"Your email is blank",Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
        if (trimPassword?.text.toString().isEmpty()){
            Toast.makeText(this,"Your password is blank",Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
    }

//    private fun validateForm(trimName: TextView?, trimEmail: TextView?, trimPassword: TextView?): Boolean{
//        return when {
//            TextUtils.isEmpty(trimName?.toString()){
//               Toast.makeText(this,"Your name is empty")
//                false
//            }
//                    TextUtils.isEmpty(email){
//                showErrorSnackBar("Plesa enter an email")
//                false
//            }
//                    TextUtils.isEmpty(password){
//                showErrorSnackBar("Plesa enter a password")
//                false
//            }else{
//                true
//            }
//        }
//    }
}