package com.example.onlinegroceries.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentSelectLocationBinding

class F_SelectLocation : Fragment() {
lateinit var binding:FragmentSelectLocationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSelectLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()

        //Move to next Profile activity
        moveNextScreen()

    }

    //Move to next Profile activity
    fun moveNextScreen(){
        binding.SubmitLocation.setOnClickListener{
            findNavController().navigate(R.id.action_selectlsocation_to_profile)
            //startActivity(Intent(this, Profile::class.java))
        }
    }
}