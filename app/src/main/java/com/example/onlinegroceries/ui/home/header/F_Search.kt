package com.example.onlinegroceries.ui.home.header

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.network.data.SearchModels
import com.example.onlinegroceries.adapter.SearchAdapter
import com.example.onlinegroceries.databinding.FragmentSearchBinding
import com.example.onlinegroceries.utility.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class F_Search : Fragment() {

    lateinit var binding: FragmentSearchBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(
            inflater, container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // initialize  recyclerView
        recyclerView = binding.searchItems
        showkeyboard()
        searchProduct()

    }

    private fun searchProduct() {

        binding.search.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.search.text.toString()
                if (query.isNotEmpty()) {
                    viewModel.getSearch(query.toInt())
                    observeViewModel()
                }


            }
            return@setOnEditorActionListener false
        }
    }


    private fun observeViewModel() {

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


    private fun installViews(productList: SearchModels) {
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        productsAdapter = SearchAdapter(productList, requireContext())
        recyclerView.adapter = productsAdapter
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

    fun showkeyboard() {
        val editText = binding.search
        editText.requestFocus()
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
}

