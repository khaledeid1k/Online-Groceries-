package com.example.onlinegroceries.ui.home.header

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlinegroceries.R
import com.example.onlinegroceries.adapter.SearchAdapter
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentSearchBinding
import com.example.onlinegroceries.network.data.ProductModelItem
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.utility.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Search(viewModelClass: Class<SearchViewModel>)
    : BaseFragment<FragmentSearchBinding,SearchViewModel>(viewModelClass) {
    override fun getLayoutId() = R.layout.fragment_search

    private lateinit var productsAdapter: SearchAdapter

    override val LOG_TAG: String
        get() = "Search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            viewmodel=viewModel
            lifecycleOwner=this@Search
        }
        // initialize  recyclerView
        showKeyBoard()
        searchProduct()
    }

    private fun searchProduct() {

        binding.search.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getAllProducts()
                  hideKeyboard(binding.search)
                    observeViewModel()
            }
            return@setOnEditorActionListener false
        }
    }


    override fun observeViewModel() {

        viewModel.productsList.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { it1 ->
                    installViews(it1)
                }
                Status.LOADING -> {
                    Snackbar.make(binding.psearch, "LOADING", Snackbar.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    showSnackBar(binding.psearch, "Try again!", "RETRY", Color.RED, Color.YELLOW)
                }

            }


        }
    }


    private fun installViews(productList: ProductModelItem) {
        productsAdapter = SearchAdapter(productList, requireContext())
        binding.searchItems.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productsAdapter
        }
    }

    private fun showSnackBar(
        parent: View,
        text1: String,
        text2: String?,
        color1: Int,
        color2: Int
    ) {

        val snackBar: Snackbar = Snackbar.make(
            parent,
            text1, Snackbar.LENGTH_LONG
        )
            .setAction(text2) {
                // when click button RETRY
            }
        snackBar.setActionTextColor(color1)
        val sbView: View = snackBar.view
        sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(color2)
        snackBar.show()
    }

    private fun showKeyBoard() {
        val editText = binding.search
        editText.requestFocus()
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

