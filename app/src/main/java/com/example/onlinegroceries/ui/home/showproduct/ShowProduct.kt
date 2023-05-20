package com.example.onlinegroceries.ui.home.showproduct

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onlinegroceries.MainActivity
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseFragment
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.databinding.FragmentShowProductBinding
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.ui.home.allproducts.AllProductsAdapter
import com.example.onlinegroceries.ui.home.allproducts.AllProductsViewModel
import com.example.onlinegroceries.ui.home.header.search.SearchViewModel
import com.example.onlinegroceries.utility.extention.closeFragment
import com.example.onlinegroceries.utility.extention.makeGone
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShowProduct() : BaseFragment<FragmentShowProductBinding, ShowProductViewModel>(
) {
    override fun getLayoutId() = R.layout.fragment_show_product
    override val viewModelClass= ShowProductViewModel::class.java
    override val LOG_TAG = "ShowProduct"

    private var productQuantity = 1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inti()
    }


    override fun onResume() {
        super.onResume()
        val mainActivity = activity as? MainActivity
        mainActivity?.hideBottomNavigation()
    }

    private fun inti(){
        binding.backButtonSP.setOnClickListener {
            closeFragment()
        }
        binding.shareProduct.setOnClickListener { shareProduct() }
    }

    private fun shareProduct() {
        val intent = Intent(Intent.ACTION_SEND)
        val shareBody =
            getString(
                R.string.shareProduct,
                viewModel.argument.productModel.title,
                viewModel.argument.productModel.price)

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(intent)
    }

//    private fun initViews() {
//        binding.apply {
//            // change total price by quantity value in edit text.
//            productQuantityEditText.addTextChangedListener {
//                val quantity = productQuantityEditText.text.toString().trim()
//                if (quantity.isNotEmpty() && TextUtils.isDigitsOnly(quantity)) {
//                    productQuantity = quantity.toDouble().toInt()
//                    if (productQuantity > 0) {
////                        productPriceTextView.text =
////                            getString(R.string.price, (product!!.price * productQuantity))
//                    }
//                }
//            }
//
////            specificProductImage.transitionName = product!!.image
//        }
//    }


}