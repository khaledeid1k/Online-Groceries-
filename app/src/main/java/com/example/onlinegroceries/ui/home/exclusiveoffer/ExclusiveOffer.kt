package com.example.onlinegroceries.ui.home.exclusiveoffer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.R
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.databinding.FragmentExclusiveOfferBinding
import com.example.onlinegroceries.ui.home.ProductsViewModel
import com.example.onlinegroceries.utility.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExclusiveOffer : Fragment() {

    lateinit var binding: FragmentExclusiveOfferBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExclusiveOfferBinding.inflate(inflater, container, false)

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
        moveToExclusiveScreen()


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
//        productsAdapter = ProductsAdapter(productList) {
//                y ,t->     findNavController().navigate(
//            F_HomeDirections.actionFHomeToShowProduct(y,t))
//
//        }


    //    recyclerView.adapter = productsAdapter
    }

    private fun moveToExclusiveScreen() {
        binding.ExSeeAll.setOnTouchListener { _, p1 ->
            if (p1.action == MotionEvent.ACTION_DOWN) {
//                parentFragmentManager.findFragmentById(R.id.main)
                findNavController().navigate(R.id.action_FHome_to_exclusiveOfferProducts)
            }
            return@setOnTouchListener false
        }
    }




}