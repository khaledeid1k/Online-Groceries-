package com.example.onlinegroceries.ui.singIn

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentSingInBinding
import com.google.firebase.auth.FirebaseAuth


class F_SingIn : Fragment() {
    private lateinit var binding: FragmentSingInBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSingInBinding.inflate(
            inflater, container,
            false
        )
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Initialize Permission class
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()

        moveToWriteYouPhoneNumber()

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun moveToWriteYouPhoneNumber() {
        binding.textInputLayout.setOnClickListener {
            findNavController().navigate(R.id.action_singIn_to_number)
        }
    }


}
