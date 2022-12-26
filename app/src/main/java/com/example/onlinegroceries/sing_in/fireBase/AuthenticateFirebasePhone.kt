package com.example.onlinegroceries.sing_in.fireBase

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.Verification
import com.example.onlinegroceries.databinding.ActivityNumberBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class AuthenticateFirebasePhone() {
    private val TAG: String="lol"
    private lateinit var context: Context
   lateinit var activity: AppCompatActivity
    private lateinit  var phoneNumber : String
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    constructor(
        context: Context,
        phoneNumber: String,
        auth: FirebaseAuth,
        activity: AppCompatActivity
    ) : this() {

    this.context=context
    this.phoneNumber=phoneNumber
    this.auth=auth
    this.activity=activity

}


    /**
     * ? PhoneAuthProvider.verifyPhoneNumber method to request that
    Firebase verify the user's phone number. */
     fun startPhoneNumberVerification( phoneNumber: String) {

        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks

        PhoneAuthProvider.verifyPhoneNumber(options.build())
        // [END start_phone_auth]
    }


    // Initialize phone auth callbacks
    // [START phone_auth_callbacks]
    /**
    ? the callback functions that handle the results of the request
     */
    private val callbacks = object : PhoneAuthProvider
    .OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
                Log.d(TAG, "khaled onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
                Log.w(TAG, "khaled onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
                Log.d(TAG, "khaled onCodeSent:$verificationId")
            val codeIntent = Intent(context, Verification::class.java)
            codeIntent.putExtra("OPT",verificationId)
            codeIntent.putExtra("resendToken",token)
            codeIntent.putExtra("phoneNumber",phoneNumber)
            context.startActivity(codeIntent)
            // [END phone_auth_callbacks]
        }
    }


    //update UI for user statute
    private fun updateUI(user: FirebaseUser? = auth.currentUser) {
        if(user!=null){
            // move automatically to main page
            Toast.makeText(context, "Move to Home Screen ", Toast.LENGTH_SHORT).show()
        }
    }


    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener( Number()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   Log.d(TAG, "khaled signInWithCredential:success")
                   // Toast.makeText(context, "Move to Home Screen ", Toast.LENGTH_SHORT).show()

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d(TAG, "khaled signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI

                }
            }
    }
    // [END sign_in_with_phone]




}