package com.example.trello.activitys

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.trello.R
import com.example.trello.firebase.FireStoreClass
import com.example.trello.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {

    private var name: TextView? = null
    private var email: TextView? = null
    private var password: TextView? = null
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

    fun userRegisteredSuccess(){
        Toast.makeText(
            this,
            "you have succesfully registered",
            Toast.LENGTH_LONG
        ).show()
        FirebaseAuth.getInstance().signOut()
        finish()
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


        if(validateForm(name,  email, password)){
//            Toast.makeText(this@SignUpActivity,"Now we can register a new user",Toast.LENGTH_LONG).show()
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                email?.text.toString(),
                password?.text.toString()
            ).addOnCompleteListener { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registerdEmail = firebaseUser.email
                    val user = User(firebaseUser.uid, name.toString(), registerdEmail.toString())
//                    FirebaseAuth.getInstance().signOut()
//                    finish()
                    FireStoreClass().registerUser(this, user)
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    private fun validateForm(name: TextView?, email: TextView?, password: TextView?): Boolean {
        if (name?.text.toString().isEmpty()){
            Toast.makeText(this,"Your name is blank",Toast.LENGTH_LONG).show()
            return false
        }else if(email?.text.toString().isEmpty()){
            Toast.makeText(this,"Your email is blank",Toast.LENGTH_LONG).show()
            return false
        }else if (password?.text.toString().isEmpty()){
            Toast.makeText(this,"Your password is blank",Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
    }

}