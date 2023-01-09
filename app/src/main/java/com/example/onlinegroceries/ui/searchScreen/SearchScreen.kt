package com.example.onlinegroceries.ui.searchScreen

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.Network.data.ProductModel
import com.example.onlinegroceries.adapter.SearchAdapter
import com.example.onlinegroceries.databinding.ActivitySearchScreenBinding
import com.example.onlinegroceries.ui.main.ProductsViewModel
import com.example.onlinegroceries.utility.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
     private lateinit var searchAdapter: SearchAdapter
    private lateinit  var binding : ActivitySearchScreenBinding
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hidden toolbar
        supportActionBar?.hide()

        // initialize  recyclerView
        recyclerView=binding.searchItems

        observeViewModel()

    }


    private fun observeViewModel(){

        viewModel.productsList.observe(this){
            when(it.status){
                Status.SUCCESS-> it.data?.let { it1 ->

                    installViews(it1)
                    Log.d(TAG, "aly observeViewModel: ${it1.size}")
                }
                Status.LOADING -> {
                    Snackbar.make( binding.psearch, "LOADING",Snackbar.LENGTH_SHORT).show()


                }
                Status.ERROR -> {
                   showSnackBar(binding.psearch,"Try again!","RETRY",Color.RED,Color.YELLOW)
                }

            }



            }
        }



    private fun installViews(productList: ProductModel){
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this,2)
//
//        // to make space between columns
//        recyclerView.addItemDecoration(
//             GridSpacingItemDecoration(
//                2,
//                100,
//                10
//            )
//        )
        recyclerView.layoutManager = layoutManager
        searchAdapter = SearchAdapter(productList,applicationContext)
        recyclerView.adapter = searchAdapter
    }

    private fun showSnackBar(parent: View, text1:String, text2: String?, color1: Int, color2: Int ){

        val snackBar: Snackbar = Snackbar.make(parent,
            text1, Snackbar.LENGTH_LONG)
            .setAction(text2) {
                // when click button RETRY
            }
        snackBar.setActionTextColor(color1)
        val sbView: View = snackBar.view
        sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
         .setTextColor(color2)
        snackBar.show()
    }
}
