package com.example.onlinegroceries.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentShowProductBinding
import com.example.onlinegroceries.network.data.ProductModel
import com.example.onlinegroceries.utility.extention.closeFragment
import dagger.hilt.android.AndroidEntryPoint

class ShowProduct :BaseFragment<FragmentShowProductBinding>() {
    override fun getLayoutId()=R.layout.fragment_show_product
    private val args: ShowProductArgs by navArgs()
    private val productModelItem: ProductModel.ProductModelItem get() = args.productModel
    private var productQuantity = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            //because when update view in xml with dataBinding ,
            // the viewMode need to know lifeCycle of activity for that we add this line
            lifecycleOwner=this@ShowProduct
            fragment = this@ShowProduct
            // to initialize the value of variable in xml
            product = productModelItem
        }
    }

    override val LOG_TAG: String
        get() = "ShowProduct"

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    fun backToPreviousScreen() {
        closeFragment()
    }

    fun shareProduct() {
        val intent = Intent(Intent.ACTION_SEND)
        val shareBody =
            getString(
                R.string.shareProduct, productModelItem.title, productModelItem.price)

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