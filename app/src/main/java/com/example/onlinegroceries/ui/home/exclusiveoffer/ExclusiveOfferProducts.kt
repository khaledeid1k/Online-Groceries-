package com.example.onlinegroceries.ui.home.exclusiveoffer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.R
import com.example.onlinegroceries.adapter.ProductsAdapter
import com.example.onlinegroceries.databinding.FragmentExclusiveOfferProductsBinding
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.ui.home.ProductsViewModel
import com.example.onlinegroceries.utility.Status
import com.example.onlinegroceries.utility.extention.closeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExclusiveOfferProducts : Fragment() {
    lateinit var binding: FragmentExclusiveOfferProductsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExclusiveOfferProductsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // initialize  recyclerView
        recyclerView = binding.RecycleExclusive

        observeViewModel()
        backToHome()
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
    private fun backToHome(){
        binding.backToHomeFromEX.setOnClickListener {
                      //   parentFragmentManager.findFragmentById(R.id.AHome)
                         closeFragment()
        //              findNavController().navigate(R.id.action_exclusiveOfferProducts_to_FHome)

        }
    }
}

