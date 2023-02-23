package com.example.trello.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.trello.R
import com.example.trello.firebase.FireStoreClass
import com.example.trello.models.User
import de.hdodenhof.circleimageview.CircleImageView

class MyProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        setupActionBar()

        FireStoreClass().loadUserData(this)
    }

    private fun setupActionBar() {
        val toolbar = this.findViewById<Toolbar>(R.id.toolbar_my_profile_activity)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_clock_white_24dp)
            actionBar.title = resources.getString(R.string.my_profile)

        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun setUserDataInUI(user: User) {
        val ivUserImage = findViewById<CircleImageView>(R.id.iv_user_image)
        val etUserName = findViewById<TextView>(R.id.et_name)
        val etUserEmail = findViewById<TextView>(R.id.et_email)
        val etUserNumber = findViewById<TextView>(R.id.et_mobile)
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_user_place_holder)
            .into(ivUserImage)
        etUserName.text = user.name
        etUserEmail.text = user.email
        if (user.mobile != null) {
            etUserNumber.text = user.mobile.toString()
        }

    }

}