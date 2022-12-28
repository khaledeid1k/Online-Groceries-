package com.example.onlinegroceries.singIn.phoneNumber.fireBase

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.singIn.phoneNumber.Number
import com.example.onlinegroceries.ui.selectLocation.SelectLocation
import com.example.onlinegroceries.singIn.phoneNumber.Verification
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

    //resend code
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var OTP: String

    constructor(
        context: Context,
        phoneNumber: String,
        auth: FirebaseAuth,
        activity: AppCompatActivity,
        resendToken: PhoneAuthProvider.ForceResendingToken,
        OTP: String
    ) : this() {

    this.context=context
    this.phoneNumber=phoneNumber
    this.auth=auth
    this.activity=activity
    this.resendToken=resendToken
    this.OTP=OTP

}  constructor(
        context: Context,
        phoneNumber: String,
        auth: FirebaseAuth,
        activity: AppCompatActivity,
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
            .setCallbacks(callbacksFirstTime)          // OnVerificationStateChangedCallbacks

        PhoneAuthProvider.verifyPhoneNumber(options.build())
        // [END start_phone_auth]
    }
    fun resendVerificationCode( phoneNumber: String) {

        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacksResendCode)
            .setForceResendingToken(resendToken)// OnVerificationStateChangedCallbacks

        PhoneAuthProvider.verifyPhoneNumber(options.build())
        // [END start_phone_auth]
    }


    // Initialize phone auth callbacks
    // [START phone_auth_callbacks]
    /**
    ? the callback functions that handle the results of the request
     */
    private val callbacksFirstTime = object : PhoneAuthProvider
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

 private val callbacksResendCode = object : PhoneAuthProvider
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
            OTP=verificationId
            resendToken=token
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
    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener( Number()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   Log.d(TAG, "khaled signInWithCredential:success")
               //     val user = task.result?.user
                    val signInSuccess = Intent(context, SelectLocation::class.java)
                    context.startActivity(signInSuccess)
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