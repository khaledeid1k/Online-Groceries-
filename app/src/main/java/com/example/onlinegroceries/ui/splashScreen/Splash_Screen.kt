package com.example.onlinegroceries.ui.splashScreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.onlinegroceries.R


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hidden toolbar
        supportActionBar?.hide()

        viewActivityForTime()



    }

    // make splash screen for seconds time
    private fun viewActivityForTime(){
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent=Intent(this, Onbording::class.java)
                startActivity(intent)
                finish()
            }

            ,3000)
    }

    // Show splash screen for seconds of time
    override fun onDestroy() {
        super.onDestroy()
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.putBoolean("first_time", true)
        editor.commit()
    }

}