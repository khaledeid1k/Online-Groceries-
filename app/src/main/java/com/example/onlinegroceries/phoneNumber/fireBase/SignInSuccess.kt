package com.example.onlinegroceries.phoneNumber.fireBase

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.onlinegroceries.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential


class SignInSuccess(
    private val auth: FirebaseAuth,
    val activity: FragmentActivity,
    private val navController: NavController,
    private val credential: PhoneAuthCredential
) {

    fun signInWithPhoneAuthCredential() {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with
                    // the signed-in user's information

                    navController.navigate(R.id.selectLocation)

                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI

                }
            }


    }

    //update UI for user statute
    private fun updateUI(user: FirebaseUser? = auth.currentUser) {
        if (user != null) {
            // move automatically to main page
            //  Toast.makeText(context, "Move to Home Screen ", Toast.LENGTH_SHORT).show()
        }
    }


}