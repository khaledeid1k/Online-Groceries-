package com.example.onlinegroceries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.adapter.OnClick
import com.example.onlinegroceries.adapter.ParentAdapter
import com.example.onlinegroceries.databinding.FragmentHomeBinding
import com.example.onlinegroceries.network.data.ParentItem
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.utility.Status
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

class Home : Fragment() , OnClick  {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        observeViewModel()

    }
    fun lol(product: ProductModelResponse){
        val list= mutableListOf<ParentItem<ProductModelResponse>>()
        list.add(ParentItem.HEADER(product))
        list.add(ParentItem.EXCLUSIVEOFFER(product))
        list.add(ParentItem.BESTSELLING(product))
        list.add(ParentItem.GROCERIES(product))
        list.add(ParentItem.BOTTOM_HOME(product))
        val parentAdapter=ParentAdapter(this,list,this)
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

    override fun onClickItem(l: ProductModelResponse) {
        val action = HomeDirections.actionFHomeToShowProduct(l)
        findNavController().navigate(action)
    }


}


