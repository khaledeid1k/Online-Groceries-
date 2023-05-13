package com.example.onlinegroceries.ui.home.header.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar


class Search
    : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override fun getLayoutId() = R.layout.fragment_search
    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    private lateinit var productsAdapter: SearchAdapter

    override val LOG_TAG: String
        get() = "Search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            viewmodel=viewModel
            lifecycleOwner=viewLifecycleOwner
        }
        // initialize  recyclerView
        showKeyBoard()
        searchProduct()
    }

    private fun searchProduct() {

        binding.search.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_SEARCH) {
//                    viewModel.getAllProducts()
                  hideKeyboard(binding.search)
//                    observeViewModel()
            }
            return@setOnEditorActionListener false
        }
    }


//     fun observeViewModel() {
//
//        viewModel.productsList.observe(requireActivity()) {
//            when (it.status) {
//                Status.SUCCESS -> it.data?.let { it1 ->
//                 //   installViews(it1)
//                }
//                Status.LOADING -> {
//                    Snackbar.make(binding.psearch, "LOADING", Snackbar.LENGTH_SHORT).show()
//                }
//                Status.ERROR -> {
//                    showSnackBar(binding.psearch, "Try again!", "RETRY", Color.RED, Color.YELLOW)
//                }
//
//            }
//
//
//        }
//    }


//    private fun installViews(productList: List<ProductModelItem>) {
//        productsAdapter = SearchAdapter(productList)
//        binding.searchItems.apply {
//            layoutManager = GridLayoutManager(requireContext(), 2)
//            adapter = productsAdapter
//        }
//    }

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

