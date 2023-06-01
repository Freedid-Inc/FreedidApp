package com.example.freedidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityHolder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)

        supportActionBar?.hide()

        var navController = findNavController(R.id.fragment)
        var bottomNavigationItemView = findViewById<BottomNavigationView>(R.id.nav_bar)

        bottomNavigationItemView.setupWithNavController(navController)
    }
}