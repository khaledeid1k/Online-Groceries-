package com.example.onlinegroceries.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.R
import com.example.onlinegroceries.OnClick
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentHomeBinding
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.utility.Status

class Home
    : BaseFragment<FragmentHomeBinding, ProductsViewModel>(

), OnClick {
    override val viewModelClass: Class<ProductsViewModel>
    get() = ProductsViewModel::class.java
    override val LOG_TAG: String="d"
    override fun getLayoutId()=R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        observeViewModel()

    }
    private fun lol(product: List<ProductModelItem>){
        val list= mutableListOf<ParentItem>()
        list.add(ParentItem.HEADER())
        list.add(ParentItem.EXCLUSIVEOFFER(product))
        list.add(ParentItem.BESTSELLING(product))
        list.add(ParentItem.GROCERIES(product))
        list.add(ParentItem.BOTTOM_HOME(product))
        val parentAdapter= ParentAdapter(this,list,this)
        binding.PRecyclerView.adapter=parentAdapter
    }
     private fun observeViewModel() {

        viewModel.productsList.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { it1 ->
                    //   installViews(it1)
                lol(it1)

                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        }
    }

    override fun onClickItem(l: ProductModelItem) {
        val action = HomeDirections.actionFHomeToShowProduct(l)
        findNavController().navigate(action)
    }


}


