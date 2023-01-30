package com.example.onlinegroceries.ui.main

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.onlinegroceries.databinding.FragmentVerificationBinding
import com.example.onlinegroceries.singIn.phoneNumber.fireBase.ResendVerificationCode
import com.example.onlinegroceries.singIn.phoneNumber.fireBase.SignInSuccess
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class F_Verification : Fragment() {
    lateinit var binding: FragmentVerificationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var OTP: String
    private lateinit var phoneNumber: String
    private lateinit var code: String
    private lateinit var authenticateFirebasePhone: ResendVerificationCode
    private lateinit var prefs: SharedPreferences
    private lateinit var navController: NavController
    private val args: F_VerificationArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVerificationBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // Initialize SharedPreferences
        prefs = requireActivity().getSharedPreferences("phone", MODE_PRIVATE)
        navController = NavHostFragment.findNavController(this)

        receiveArgs()


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
    private fun receiveArgs() {
        auth = FirebaseAuth.getInstance()
//        resendToken = intent.getParcelableExtra("resendToken")!!
        phoneNumber = args.phoneNumber
        OTP = args.otp


    }

    //to resend code to user if not arrived to him
    private fun resendCode() {
        binding.ResendCode.setOnClickListener {
//            authenticateFirebasePhone.resendVerificationCode(phoneNumber)
//            visibleButtonResendCode()
        }
    }

    // to check send code is true
    private fun verificationOfCode() {
        binding.VerificatOfCode.setOnClickListener {
            code = binding.CodeOfVerificat.text?.trim().toString()
            if (code.length == 6) {
                val credential: PhoneAuthCredential = PhoneAuthProvider
                    .getCredential(OTP, code)
                val success = SignInSuccess(
                    auth,
                    requireActivity(),
                    navController,
                    credential
                )
                success.signInWithPhoneAuthCredential()


            } else {
                Toast.makeText(requireActivity(), "Incorrect Code", Toast.LENGTH_SHORT).show()

            }


        }

    }


}