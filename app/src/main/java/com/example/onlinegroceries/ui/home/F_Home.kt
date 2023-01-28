package com.example.onlinegroceries.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinegroceries.databinding.FragmentHomeBinding

class   F_Home : Fragment() {
    lateinit var binding : FragmentHomeBinding

 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     // Inflate the layout for this fragment
     binding= FragmentHomeBinding.inflate(inflater, container,
         false)
     return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()



        }
    }


