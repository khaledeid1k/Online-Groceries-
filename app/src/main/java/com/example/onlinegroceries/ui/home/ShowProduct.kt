package com.example.onlinegroceries.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentShowProductBinding
import com.example.onlinegroceries.utility.extention.closeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowProduct : Fragment() {
    lateinit var binding: FragmentShowProductBinding
    private val args: ShowProductArgs by navArgs()
    private val productModelItem by lazy { args.productModel[args.position] }
    private var productQuantity = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShowProductBinding.inflate(inflater, container, false)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_show_product, container, false)
        binding.apply {
            fragment = this@ShowProduct
            product = productModelItem
            return this.root
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()

    }

    fun backToPreviousScreen() {
        closeFragment()
    }

    fun shareProduct() {
        val intent = Intent(Intent.ACTION_SEND)
        val shareBody =
            getString(
                R.string.shareProduct, productModelItem.title,
                productModelItem.price
            )

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(intent)
    }

    private fun initViews() {
        binding.apply {
            // change total price by quantity value in edit text.
            productQuantityEditText.addTextChangedListener {
                val quantity = productQuantityEditText.text.toString().trim()
                if (quantity.isNotEmpty() && TextUtils.isDigitsOnly(quantity)) {
                    productQuantity = quantity.toDouble().toInt()
                    if (productQuantity > 0) {
                        productPriceTextView.text =
                            getString(R.string.price, (product!!.price * productQuantity))
                    }
                }
            }

            specificProductImage.transitionName = product!!.image
        }
    }



}