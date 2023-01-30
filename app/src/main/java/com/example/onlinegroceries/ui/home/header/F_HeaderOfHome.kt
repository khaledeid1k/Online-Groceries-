package com.example.onlinegroceries.ui.home.header

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.onlinegroceries.network.data.SliderData
import com.example.onlinegroceries.R
import com.example.onlinegroceries.adapter.SliderAdapter
import com.example.onlinegroceries.databinding.FragmentHeaderOfHomeBinding
import com.smarteist.autoimageslider.SliderView


class F_HeaderOfHome : Fragment() {

    lateinit var binding: FragmentHeaderOfHomeBinding
    lateinit var prefs: SharedPreferences

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHeaderOfHomeBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()

        imageSlider()
        getNameUser()
        getImageUser()
        moveToSearchScreen()
    }

    private fun getNameUser() {
        prefs = requireActivity().getSharedPreferences(
            "profile", MODE_PRIVATE
        )
        binding.userNameTextView.text = prefs.getString("name", null)
    }

    private fun getImageUser() {
        val imageUrl = prefs.getString("image", null)
        Glide.with(requireActivity()).load(imageUrl).into(
            binding.userImageImageView
        )
    }

    private fun imageSlider() {
        // we are creating array list for storing our image urls.
        // we are creating array list for storing our image urls.
        val sliderDataArrayList: ArrayList<SliderData> = ArrayList()

        // initializing the slider view.

        // initializing the slider view.
        val sliderView: SliderView = binding.shopRV

        // adding the urls inside array list

        // adding the urls inside array list
        sliderDataArrayList.add(SliderData(R.drawable.banner1))
        sliderDataArrayList.add(SliderData(R.drawable.banner2))

        // passing this array list inside our adapter class.

        // passing this array list inside our adapter class.
        val adapter = SliderAdapter(requireContext(), sliderDataArrayList)

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

    private fun moveToSearchScreen() {
        binding.shopSearchEditText.setOnTouchListener { p0, p1 ->
            if (p1.action == MotionEvent.ACTION_DOWN) {
                parentFragmentManager.findFragmentById(R.id.AHome)
                findNavController().navigate(R.id.action_FHome_to_f_Search2)
            }
            return@setOnTouchListener false
        }
    }
}

