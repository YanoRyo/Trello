package com.example.trello.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.trello.R
import com.example.trello.firebase.FireStoreClass
import com.example.trello.models.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        val navView = this.findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        FireStoreClass().loadUserData(this)

        // 「android.R.color.transparent」を指定することで、ステータスバーを透過させている
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    }

    private fun setupActionBar() {
        val toolbar = this.findViewById<Toolbar>(R.id.toolbar_main_activity)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        toolbar.setNavigationOnClickListener {
            toggleDrawer()
        }
    }

    private fun toggleDrawer() {
        val drawLayout = findViewById<DrawerLayout>(R.id.draw_layout)
        if (drawLayout.isDrawerOpen(GravityCompat.START)) {
            drawLayout.closeDrawer(GravityCompat.START)
        } else {
            drawLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        val drawLayout = findViewById<DrawerLayout>(R.id.draw_layout)
        if (drawLayout.isDrawerOpen(GravityCompat.START)) {
            drawLayout.closeDrawer(GravityCompat.START)
        } else {
            doubleBackToExit()
        }
    }

    fun updateNavigationUserDetails(user: User) {
        val navUserImage = findViewById<CircleImageView>(R.id.nav_user_image)
        val tvUserName = findViewById<TextView>(R.id.tv_user_name)
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_user_place_holder)
            .into(navUserImage);
        tvUserName.text = user.name
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawLayout = findViewById<DrawerLayout>(R.id.draw_layout)
        when (item.itemId) {
            R.id.nav_my_profile -> {
                startActivity(Intent(this, MyProfileActivity::class.java))
            }
            R.id.nav_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        drawLayout.closeDrawer(GravityCompat.START)
        return true
    }

}