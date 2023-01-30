package com.example.onlinegroceries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.adapter.GroceriesAdapter
import com.example.onlinegroceries.databinding.FragmentGroceriesBinding
import com.example.onlinegroceries.utility.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class F_Groceries : Fragment() {

    lateinit var binding: FragmentGroceriesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: GroceriesAdapter
    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroceriesBinding.inflate(inflater, container,
            false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // initialize  recyclerView
        recyclerView = binding.RecycleGroceries

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
        productsAdapter = GroceriesAdapter(productList, requireContext())
        recyclerView.adapter = productsAdapter
    }

}