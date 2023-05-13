package com.example.onlinegroceries.ui.home.electronics

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentElectronicsBinding
import com.example.onlinegroceries.ui.home.ProductsViewModel

class Electronics
    : BaseFragment<FragmentElectronicsBinding, ProductsViewModel>() {
    override val LOG_TAG: String="F_BestSelling"
    override fun getLayoutId()= R.layout.fragment_electronics
    override val viewModelClass: Class<ProductsViewModel>
        get() = ProductsViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshData()


    }


//    override fun observeViewModel() {
//
//        viewModel.productsList.observe(requireActivity()) {
//            when (it.status) {
//                Status.SUCCESS -> it.data?.let { it1 ->
//                 //   installViews(it1)
//                    Home().lol(it1.productModelItem)
//
//                }
//                Status.LOADING -> {
//                }
//                Status.ERROR -> {
//                }
//            }
//        }
//    }


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