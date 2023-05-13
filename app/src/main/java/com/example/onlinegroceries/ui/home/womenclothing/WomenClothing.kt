package com.example.onlinegroceries.ui.home.womenclothing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onlinegroceries.databinding.FragmentWomenclothingBinding
import com.example.onlinegroceries.ui.home.ProductsViewModel
import com.example.onlinegroceries.utility.Status

class WomenClothing : Fragment() {

    lateinit var binding: FragmentWomenclothingBinding
    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWomenclothingBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // initialize  recyclerView

        observeViewModel()
    }


    private fun observeViewModel() {

        viewModel.productsList.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { it1 ->
                }
                Status.LOADING -> {


                }
                Status.ERROR -> {
                }

            }


        }
    }




}