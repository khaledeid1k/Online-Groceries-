package com.example.onlinegroceries.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.onlinegroceries.ui.home.ProductsViewModel

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    abstract val LOG_TAG: String
    private var _binding: ViewBinding? = null
    abstract fun getLayoutId(): Int


    protected val binding get() = _binding as VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(requireActivity(),getLayoutId())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        observeViewModel()
    }

    //  abstract fun installViews(productList: ProductResponse)
    protected fun log(value: String) {
        Log.d(LOG_TAG, value)
    }

    abstract fun observeViewModel()


}