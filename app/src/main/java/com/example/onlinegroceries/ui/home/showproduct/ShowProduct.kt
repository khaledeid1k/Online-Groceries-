package com.example.onlinegroceries.ui.home.showproduct

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.navArgs
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.databinding.FragmentShowProductBinding
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.ui.home.header.search.SearchViewModel
import com.example.onlinegroceries.utility.extention.closeFragment

class ShowProduct :BaseFragment<FragmentShowProductBinding, SearchViewModel>(
) {
    override fun getLayoutId()=R.layout.fragment_show_product
    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java
    private val args: ShowProductArgs by navArgs()
    private val productModelItem: ProductModelItem get() = args.productModel
    private var productQuantity = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            //because when update view in xml with dataBinding ,
            // the viewMode need to know lifeCycle of activity for that we add this line
            lifecycleOwner=this@ShowProduct
            fragment = this@ShowProduct
            // to initialize the value of variable in xml
            model = productModelItem
        }
    }

    override val LOG_TAG: String
        get() = "ShowProduct"



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
//                        productPriceTextView.text =
//                            getString(R.string.price, (product!!.price * productQuantity))
                    }
                }
            }

//            specificProductImage.transitionName = product!!.image
        }
    }



}