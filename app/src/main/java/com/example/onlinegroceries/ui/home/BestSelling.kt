package com.example.onlinegroceries.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentBestSellingBinding
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.utility.Status

class BestSelling(viewModelClass: Class<ProductsViewModel>)
    : BaseFragment<FragmentBestSellingBinding,ProductsViewModel>(viewModelClass) {
    override val LOG_TAG: String="F_BestSelling"
    override fun getLayoutId()= R.layout.fragment_best_selling

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