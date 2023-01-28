package com.example.onlinegroceries.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinegroceries.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class A_HomeContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_container)
    }
}