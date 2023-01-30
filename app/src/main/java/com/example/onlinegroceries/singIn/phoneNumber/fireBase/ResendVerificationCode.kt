package com.example.onlinegroceries.singIn.phoneNumber.fireBase

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class ResendVerificationCode(
    val context: Context,
    val phoneNumber: String,
    val auth: FirebaseAuth,
    val activity: FragmentActivity,
    val navController: NavController,
    var OTP: String,
    var resendToken: PhoneAuthProvider.ForceResendingToken,

    ) {


    /**
     * ? PhoneAuthProvider.verifyPhoneNumber method to request that
    Firebase verify the user's phone number. */
    fun resendVerificationCode(phoneNumber: String) {

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


    private val callbacksResendCode = object : PhoneAuthProvider
    .OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            val success = SignInSuccess(auth, activity, navController, credential)
            success.signInWithPhoneAuthCredential()
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

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
            OTP = verificationId
            resendToken = token
        }
    }


}