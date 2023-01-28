package com.example.onlinegroceries.ui.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.onlinegroceries.databinding.FragmentNumberBinding
import com.example.onlinegroceries.singIn.phoneNumber.fireBase.StartPhoneNumberVerification
import com.google.firebase.auth.FirebaseAuth


class F_PhoneNumber : Fragment() {
    private var codeCountry: String = "+20"
    private lateinit var binding: FragmentNumberBinding
    private lateinit var authenticateFirebasePhone   : StartPhoneNumberVerification
    private lateinit var auth: FirebaseAuth
    private lateinit var  phoneNumber: String
    private lateinit var navController :NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentNumberBinding.inflate(inflater, container,
            false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        navController=findNavController(this)

        moveToVerification()

        countryCode()

    }


    //Initialize of constructor's class to authenticate FirebasePhone
    private fun addValueOfAuthenticateFirebasePhone(){
        authenticateFirebasePhone=
            StartPhoneNumberVerification(
                requireActivity(),
                phoneNumber,
                auth,
                requireActivity(),
               navController
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
            addValueOfAuthenticateFirebasePhone()
            if(phoneNumber.length>=4){
                phoneNumber= "$codeCountry $phoneNumber"
                Log.d("s", "khaled moveToVerification: $phoneNumber")

                authenticateFirebasePhone.
                    startPhoneNumberVerification(phoneNumber)
            }else{
                Toast.makeText(requireActivity(),"Please enter Your number ", Toast.LENGTH_SHORT).show()
            }

        }

    }






}



