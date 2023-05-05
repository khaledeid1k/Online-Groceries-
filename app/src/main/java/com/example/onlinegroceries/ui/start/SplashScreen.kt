package com.example.onlinegroceries.ui.start

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentSplashScreenBinding

class SplashScreen : Fragment() {
    lateinit var binding: FragmentSplashScreenBinding
    lateinit var prefs: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        viewActivityForTime()

    }

    private fun viewActivityForTime() {
        // show splash screen for first time
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        if (prefs.getBoolean("first_time", false)) {
            findNavController().navigate(
                R.id.action_splashScreen_to_onBoarding
            )
        }
        Handler(Looper.getMainLooper()).postDelayed(
            {
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(
                        R.id.action_splashScreen_to_onBoarding
                    )
                }

            }, 3000)


    }

    // Show splash screen for seconds of time
    override fun onDestroy() {
        super.onDestroy()
        prefs.edit().putBoolean("first_time", true).commit()
        //binding = null
    }

}