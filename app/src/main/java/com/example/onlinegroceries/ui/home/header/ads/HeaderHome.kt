package com.example.onlinegroceries.ui.home.header.ads

import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentHeaderOfHomeBinding
import com.example.onlinegroceries.model.SliderData
import com.smarteist.autoimageslider.SliderView

class HeaderHome(private val fragment: Fragment) {
    fun imageSlider(binding: FragmentHeaderOfHomeBinding) {
        // we are creating array list for storing our image urls.
        // we are creating array list for storing our image urls.
        val sliderDataArrayList: ArrayList<SliderData> = ArrayList()

        // initializing the slider view.

        // initializing the slider view.
        val sliderView: SliderView = binding.shopRV

        // adding the urls inside array list

        // adding the urls inside array list
        sliderDataArrayList.add(SliderData(R.drawable.banner))
        sliderDataArrayList.add(SliderData(R.drawable.banner2))

        // passing this array list inside our adapter class.

        // passing this array list inside our adapter class.
        val adapter = SliderAdapter(fragment.requireContext(), sliderDataArrayList)

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

        // below method is used to
        // setadapter to sliderview.

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter)

        // below method is use to set
        // scroll time in seconds.

        // below method is use to set
        // scroll time in seconds.
        sliderView.scrollTimeInSec = 3

        // to set it scrollable automatically
        // we use below method.

        // to set it scrollable automatically
        // we use below method.
        sliderView.isAutoCycle = true

        // to start autocycle below method is used.

        // to start autocycle below method is used.
        sliderView.startAutoCycle()
    }

    fun moveToSearchScreen(binding: FragmentHeaderOfHomeBinding) {
        binding.shopSearchEditText.setOnTouchListener { _, p1 ->
            if (p1.action == MotionEvent.ACTION_DOWN) {
                // parentFragmentManager.findFragmentById(R.id.AHome)
                fragment.findNavController().navigate(R.id.home_to_search)
            }
            return@setOnTouchListener false
        }
    }

}