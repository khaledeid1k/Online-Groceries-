package com.example.onlinegroceries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.adapter.ProductsAdapter
import com.example.onlinegroceries.databinding.FragmentBottomOfHomeBinding
import com.example.onlinegroceries.utility.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class F_BottomHome : Fragment() {

    lateinit var binding: FragmentBottomOfHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter
    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomOfHomeBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // initialize  recyclerView
        recyclerView = binding.RecycleBottomHome

        observeViewModel()
    }


    private fun observeViewModel() {

        viewModel.productsList.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { it1 ->
                    installViews(it1)
                }
                Status.LOADING -> {


                }
                Status.ERROR -> {
                }

            }


        }
    }


    private fun installViews(productList: ProductResponse) {
        productsAdapter = ProductsAdapter(productList)
        {
            y,t->
        }
        recyclerView.adapter = productsAdapter
    }

}