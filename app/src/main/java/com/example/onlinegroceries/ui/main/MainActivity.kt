package com.example.onlinegroceries.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinegroceries.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in_container)
        // Hidden toolbar
        supportActionBar?.hide()


    }


}