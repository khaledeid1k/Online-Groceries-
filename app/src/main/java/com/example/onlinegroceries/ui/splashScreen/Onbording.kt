package com.example.onlinegroceries.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.singIn.Sing_in
import com.example.onlinegroceries.databinding.ActivityOnbordingBinding

class Onbording : AppCompatActivity() {
    lateinit var binding: ActivityOnbordingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataBinding
       binding = ActivityOnbordingBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        // Hidden toolbar
        supportActionBar?.hide()

        getstart_of_sing_in()


    }

    fun getstart_of_sing_in(){
        binding.getStart.setOnClickListener{
            startActivity(Intent(this, Sing_in::class.java))

        }
    }


}