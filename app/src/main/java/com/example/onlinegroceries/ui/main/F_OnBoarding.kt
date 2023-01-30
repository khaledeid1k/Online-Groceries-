package com.example.onlinegroceries.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentOnboardingBinding
import com.google.firebase.auth.FirebaseAuth


class F_OnBoarding : Fragment() {
    lateinit var binding: FragmentOnboardingBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        startApp()
    }

    // Check if user is signed in (non-null) and update UI accordingly.
    private fun startApp() {
        binding.getStart.setOnClickListener {
            val currentUser = auth.currentUser
            //   if(currentUser!=null){
            it?.findNavController()?.navigate(
                R.id
                    .action_onbording_to_homeG
            )
            //  }
//            else{
//                findNavController().navigate(
//                    R.id.action_onbording_to_singIn)
//            }
        }
    }

}