package com.example.onlinegroceries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentBestSellingBinding
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.ui.home.header.SearchViewModel
import com.example.onlinegroceries.utility.Status
import dagger.hilt.android.AndroidEntryPoint

class BestSelling : BaseFragment<FragmentBestSellingBinding>() {
    override val LOG_TAG: String="F_BestSelling"
    override fun getLayoutId()= R.layout.fragment_best_selling
    private val viewModel: ProductsViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshData()


    }


    override fun observeViewModel() {

        viewModel.productsList.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { it1 ->
                 //   installViews(it1)
                    Home().lol(it1)

                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        }
    }


    private fun refreshData(){
        binding.swiperefresh.setOnRefreshListener {
            SwipeRefreshLayout.OnRefreshListener{
                Toast.makeText(requireContext(), "lol", Toast.LENGTH_SHORT).show()
             //   productsAdapter.updateData()

                binding.swiperefresh.isRefreshing=false
            }
        }
    }


}