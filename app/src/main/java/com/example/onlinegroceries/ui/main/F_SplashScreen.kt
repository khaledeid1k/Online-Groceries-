package com.example.onlinegroceries.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentSplachScreenBinding

class F_SplashScreen : Fragment() {
lateinit var binding: FragmentSplachScreenBinding
    lateinit var prefs: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentSplachScreenBinding.inflate(inflater, container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        viewActivityForTime()

    }
    private fun viewActivityForTime(){
        // show splash screen for first time
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        if(prefs.getBoolean("first_time", false))
        {
            findNavController().navigate(
                R.id.action_splachScreen_to_onbording)
        }
        Handler(Looper.getMainLooper()).postDelayed(
            {
                lifecycleScope.launchWhenResumed {
                findNavController().navigate(
                    R.id.action_splachScreen_to_onbording)
                }

            }

            ,3000)


    }

    // Show splash screen for seconds of time
    override fun onDestroy() {
        super.onDestroy()
            prefs.edit().putBoolean("first_time", true).commit()
        //binding = null
    }

}