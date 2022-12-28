package com.example.onlinegroceries.ui.selectLocation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinegroceries.databinding.ActivitySelectLocationBinding
import com.example.onlinegroceries.singIn.Profile

class SelectLocation : AppCompatActivity() {
  lateinit  var binding : ActivitySelectLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Hidden toolbar
        supportActionBar?.hide()
        binding.SubmitLocation.setOnClickListener{
            startActivity(Intent(this,Profile::class.java))
        }
    }

}