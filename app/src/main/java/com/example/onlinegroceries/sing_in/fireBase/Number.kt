package com.example.onlinegroceries.sing_in.fireBase

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.databinding.ActivityNumberBinding
import com.google.firebase.auth.*


class Number : AppCompatActivity() {
    private lateinit var binding: ActivityNumberBinding
    private lateinit var authenticateFirebasePhone   : AuthenticateFirebasePhone
    private lateinit var auth: FirebaseAuth
    private var  phoneNumber: String="5515646"

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

        authenticateFirebasePhone=
            AuthenticateFirebasePhone(
                this,
                phoneNumber,
                auth,
                this
            )

        moveToVerification()



    }







    @RequiresApi(Build.VERSION_CODES.M)
    //if Permission Is Granted will move to verification page
    fun moveToVerification() {
        binding.fab.setOnClickListener {
            phoneNumber=   binding.YourNumberPhone.text?.trim().toString()
            if(phoneNumber.length>=4){
                phoneNumber="+20$phoneNumber"
                authenticateFirebasePhone.
                    startPhoneNumberVerification(phoneNumber)


            }else{
                Toast.makeText(this,"Please enter Your number ", Toast.LENGTH_SHORT).show()
            }

        }

    }



    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

    }
    // [END on_start_check_user]



}



