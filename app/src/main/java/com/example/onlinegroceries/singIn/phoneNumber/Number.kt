package com.example.onlinegroceries.singIn.phoneNumber

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.databinding.ActivityNumberBinding
import com.example.onlinegroceries.singIn.phoneNumber.fireBase.AuthenticateFirebasePhone
import com.google.firebase.auth.*


class Number : AppCompatActivity() {
    private var codeCountry: String = "+20"
    private lateinit var binding: ActivityNumberBinding
    private lateinit var authenticateFirebasePhone   : AuthenticateFirebasePhone
    private lateinit var auth: FirebaseAuth
    private var  phoneNumber: String=""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        // Hidden toolbar
        supportActionBar?.hide()

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        addValueOfAuthenticateFirebasePhone()

        moveToVerification()

        countryCode()


    }

    //Initialize of constructor's class to authenticate FirebasePhone
    private fun addValueOfAuthenticateFirebasePhone(){
        authenticateFirebasePhone=
            AuthenticateFirebasePhone(
                this,
                phoneNumber,
                auth,
                this
            )
    }

    //add country code to phone number
    private fun countryCode(){
        binding.countryPicker.setOnCountryChangeListener {
            codeCountry= binding.countryPicker.selectedCountryCode
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    //if Permission Is Granted will move to verification page
    fun moveToVerification() {
        binding.fab.setOnClickListener {
            phoneNumber=   binding.YourNumberPhone.text?.trim().toString()
            if(phoneNumber.length>=4){
                phoneNumber= "+$codeCountry $phoneNumber"
                Log.d("s", "khaled moveToVerification: $phoneNumber")
                authenticateFirebasePhone.
                    startPhoneNumberVerification(phoneNumber)


            }else{
                Toast.makeText(this,"Please enter Your number ", Toast.LENGTH_SHORT).show()
            }

        }

    }






}



