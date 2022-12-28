package com.example.onlinegroceries.ui.HomeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.onlinegroceries.R

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
    }
}