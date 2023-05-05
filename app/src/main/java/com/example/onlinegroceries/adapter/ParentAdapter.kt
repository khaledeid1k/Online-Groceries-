package com.example.onlinegroceries.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.onlinegroceries.R
import com.example.onlinegroceries.adapter.ParentAdapter.Companion.Header
import com.example.onlinegroceries.databinding.FragmentBestSellingBinding
import com.example.onlinegroceries.databinding.FragmentBottomOfHomeBinding
import com.example.onlinegroceries.databinding.FragmentExclusiveOfferBinding
import com.example.onlinegroceries.databinding.FragmentGroceriesBinding
import com.example.onlinegroceries.databinding.FragmentHeaderOfHomeBinding
import com.example.onlinegroceries.network.data.ParentItem
import com.example.onlinegroceries.network.data.ProductModel
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.network.data.SliderData
import com.example.onlinegroceries.ui.home.HeaderHome
import com.example.onlinegroceries.utility.Constants
import com.example.onlinegroceries.utility.SharedPreferencesHelper
import com.smarteist.autoimageslider.SliderView

class ParentAdapter(
    private val fragment:Fragment,
    private var items: List<ParentItem<ProductModel>>,
    private val listener : OnClick
    ) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {
    private val shared=SharedPreferencesHelper(fragment.requireContext())
    private val headerHome=HeaderHome(fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return when (viewType) {
            Header -> {
                HeaderViewHolder(
                    FragmentHeaderOfHomeBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }
            EXCLUSIVE_OFFER -> {
                ExclusiveViewHolder(
                    FragmentExclusiveOfferBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }
            BEST_SELLING -> {
                BestSellingViewHolder(
                    FragmentBestSellingBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }

            GROCERIES -> {
                GroceriesViewHolder(
                    FragmentGroceriesBinding.inflate(
                        inflate,
                        parent, false
                    )
                )

            }
            else -> {
                BottomOfHomeViewHolder(
                    FragmentBottomOfHomeBinding.inflate(
                        inflate,
                        parent, false
                    )
                )

            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.binding.userNameTextView.text = shared.get(Constants.prefsName)
                Glide.with(fragment).load(shared.get(Constants.prefsImage)).into(
                    holder.binding.userImageImageView
                )
                headerHome.imageSlider(holder.binding)
                headerHome.moveToSearchScreen(holder.binding)
            }
            is ExclusiveViewHolder -> {
                val exclusiveAdapter = ProductsAdapter(items[position].list as ProductResponse)
                {
                    listener.onClickItem(it)
                }
                holder.binding.RecycleExclusive.adapter = exclusiveAdapter
            }
            is BestSellingViewHolder -> {
                val productsAdapter = ProductsAdapter(items[position].list as ProductResponse)
                {
                    listener.onClickItem(it)
                }
                holder.binding.RecycleBestSelling.adapter = productsAdapter


            }
            is GroceriesViewHolder -> {
                val groceriesAdapter =
                    GroceriesAdapter(items[position].list as ProductResponse) {
                        listener.onClickItem(it)
                    }
                holder.binding.RecycleGroceries.adapter = groceriesAdapter
            }
            is BottomOfHomeViewHolder -> {
                val bottomOfHomeAdapter =
                    GroceriesAdapter(items[position].list as ProductResponse) {
                        listener.onClickItem(it)
                    }
                holder.binding.RecycleBottomHome.adapter = bottomOfHomeAdapter
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ParentItem.HEADER-> Header
            is ParentItem.EXCLUSIVEOFFER -> EXCLUSIVE_OFFER
            is ParentItem.BESTSELLING -> BEST_SELLING
            is ParentItem.GROCERIES -> GROCERIES
            else -> BOTTOM_HOME
        }
    }

    abstract class ParentViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    class HeaderViewHolder(val binding: FragmentHeaderOfHomeBinding) :
        ParentViewHolder(binding){

        }

    class ExclusiveViewHolder(val binding: FragmentExclusiveOfferBinding) :
        ParentViewHolder(binding)


    class BestSellingViewHolder(val binding: FragmentBestSellingBinding) :
        ParentViewHolder(binding)


    class GroceriesViewHolder(val binding: FragmentGroceriesBinding) :
        ParentViewHolder(binding)


    class BottomOfHomeViewHolder(val binding: FragmentBottomOfHomeBinding) :
        ParentViewHolder(binding)


    companion object {
        const val Header = R.layout.fragment_header_of_home
        const val EXCLUSIVE_OFFER = R.layout.fragment_exclusive_offer
        const val BEST_SELLING = R.layout.fragment_best_selling
        const val GROCERIES = R.layout.fragment_groceries
        const val BOTTOM_HOME = R.layout.fragment_bottom_of_home

    }


}
