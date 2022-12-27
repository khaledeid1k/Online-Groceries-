package com.example.onlinegroceries.ui.selectLocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinegroceries.databinding.ActivitySelectLocationBinding

class SelectLocation : AppCompatActivity() {
  lateinit  var binding : ActivitySelectLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}