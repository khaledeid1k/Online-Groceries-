package com.example.onlinegroceries.ui.home.allproducts

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentAllProductsBinding
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.ui.home.ItemStandInteractionInter
import com.example.onlinegroceries.utility.extention.closeFragment

class FragmentAllProducts
    : BaseFragment<FragmentAllProductsBinding, AllProductsViewModel>(), ItemStandInteractionInter {

    override val LOG_TAG: String = "F_BestSelling"

    override fun getLayoutId() = R.layout.fragment_all_products

    override val viewModelClass = AllProductsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AllProductsAdapter(this, mutableListOf())
        binding.RecycleStand.adapter = adapter
        binding.backToHomeFromEX.setOnClickListener {
            closeFragment()
        }
    }

    override fun onClickItemStand(productModelItem: ProductModelItem) {
        findNavController().navigate(FragmentAllProductsDirections.allProductsToShowProduct(productModelItem)
        )

    }


//
//    private fun refreshData(){
//        binding.swiperefresh.setOnRefreshListener {
//            SwipeRefreshLayout.OnRefreshListener{
//                Toast.makeText(requireContext(), "lol", Toast.LENGTH_SHORT).show()
//             //   productsAdapter.updateData()
//
//                binding.swiperefresh.isRefreshing=false
//            }
//        }
//    }


}