package com.example.onlinegroceries

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.databinding.ActivityVerificationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider


class Verification : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var OTP: String
    private lateinit var phoneNumber: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var code: String


   lateinit var binding:ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVerificationBinding.inflate(layoutInflater)
        val root=binding.root
        setContentView(root)
        // Hidden toolbar
        supportActionBar?.hide()

        auth=FirebaseAuth.getInstance()
        OTP =intent.getStringExtra("OPT").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
         phoneNumber=intent.getStringExtra("phoneNumber")!!
        code=binding.CodeOfVerificat.text?.trim().toString()
binding.VerificatOfCode.setOnClickListener{

    if (code.length==6){

val credential :  PhoneAuthCredential=PhoneAuthProvider.getCredential(
    OTP , code
)
    }else{
   Toast.makeText(this, "Incorrect Code", Toast.LENGTH_SHORT).show()

    }
}

    }
    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(this, "Move to Home Screen ", Toast.LENGTH_SHORT).show()

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI

                }
            }
    }
    // [END sign_in_with_phone]

}