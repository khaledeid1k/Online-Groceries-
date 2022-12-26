package com.example.onlinegroceries

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hidden toolbar
        supportActionBar?.hide()
        view_activity_for_time()



    }

    fun view_activity_for_time(){
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent=Intent(this,Onbording::class.java)
                startActivity(intent)
                finish()
            }

            ,3000)
    }
    // Show splash screen one time
    override fun onDestroy() {
        super.onDestroy()
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.putBoolean("first_time", true)
        editor.commit()
    }

}