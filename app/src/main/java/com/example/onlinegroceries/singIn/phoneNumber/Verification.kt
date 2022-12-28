package com.example.onlinegroceries.singIn.phoneNumber

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.databinding.ActivityVerificationBinding
import com.example.onlinegroceries.singIn.phoneNumber.fireBase.AuthenticateFirebasePhone
import com.google.firebase.auth.*

class Verification : AppCompatActivity() {
    lateinit var binding: ActivityVerificationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var OTP: String
    private lateinit var phoneNumber: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var code: String
    private lateinit var authenticateFirebasePhone: AuthenticateFirebasePhone
    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        // Hidden toolbar
        supportActionBar?.hide()

        // Initialize SharedPreferences
        prefs = getSharedPreferences("phone", MODE_PRIVATE)

        receiveIntent()

        addValueOfAuthenticateFirebasePhone()

        visibleButtonResendCode()

        verificationOfCode()

        resendCode()
    }

    // to hidden Resend Text in first time open activity
    private fun visibleButtonResendCode() {
        binding.CodeOfVerificat.setText("")
        binding.ResendCode.visibility = View.INVISIBLE
        binding.ResendCode.isEnabled = false
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            binding.ResendCode.visibility = View.VISIBLE
            binding.ResendCode.isEnabled = true
        }, 60000)
    }

    // receive intent from AuthenticateFirebasePhone class
    private fun receiveIntent() {
        auth = FirebaseAuth.getInstance()
        OTP = intent.getStringExtra("OPT").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
        phoneNumber = intent.getStringExtra("phoneNumber")!!
        prefs.edit().putString("phoneNumber",phoneNumber).apply()

    }

    //to resend code to user if not arrived to him
    private fun resendCode(){
        binding.ResendCode.setOnClickListener {
            authenticateFirebasePhone.resendVerificationCode(phoneNumber)
            visibleButtonResendCode()
        }
    }

    // to check send code is true
    private fun verificationOfCode(){
        binding.VerificatOfCode.setOnClickListener {
            code = binding.CodeOfVerificat.text?.trim().toString()
            if (code.length == 6) {
                val credential: PhoneAuthCredential = PhoneAuthProvider
                    .getCredential(OTP, code)
                authenticateFirebasePhone.signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(this, "Incorrect Code", Toast.LENGTH_SHORT).show()

            }



        }

    }

    //Initialize of constructor's class to authenticate FirebasePhone
    private fun addValueOfAuthenticateFirebasePhone(){
        authenticateFirebasePhone =
            AuthenticateFirebasePhone(
                this,
                phoneNumber,
                auth,
                this,
                resendToken,
                OTP
            )
    }
}