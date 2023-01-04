package com.example.onlinegroceries.ui.searchScreen

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.Network.Repository.MainRepository
import com.example.onlinegroceries.Network.data.ProductModel
import com.example.onlinegroceries.Network.dataSource.remoteDataSource.RetrofitBuilder
import com.example.onlinegroceries.adapter.SearchAdapter
import com.example.onlinegroceries.databinding.ActivitySearchScreenBinding
import com.example.onlinegroceries.ui.main.MyViewModelFactory
import com.example.onlinegroceries.ui.main.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
     private lateinit var searchAdapter: SearchAdapter
    private lateinit  var binding : ActivitySearchScreenBinding

private  val viewModel : ProductsViewModel by viewModels()
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

        viewModel.getAllProducts()

        viewModel.productsList.observe(this) {
            installViews(it)
        }
        viewModel.errorMessage.observe(this) {
            Log.d("here",it.toString())
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
}