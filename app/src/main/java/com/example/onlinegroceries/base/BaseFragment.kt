package com.example.onlinegroceries.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlinegroceries.BR
import com.example.onlinegroceries.network.data.ProductModelItem
import com.example.onlinegroceries.network.data.ProductModelResponse

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel<ProductModelResponse>>(
    private val viewModelClass: Class<VM>
) : Fragment() {
    abstract val LOG_TAG: String
    lateinit var _binding: VB
    abstract fun getLayoutId(): Int

    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    protected val binding get() = _binding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        observeViewModel()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.setContentView(requireActivity(), getLayoutId())
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewModel)
        }
        return _binding.root
    }

    //  abstract fun installViews(productList: ProductResponse)
    protected fun log(value: String) {
        Log.d(LOG_TAG, value)
    }

    abstract fun observeViewModel()

}