package com.example.onlinegroceries.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.onlinegroceries.R
import com.example.onlinegroceries.OnClick
import com.example.onlinegroceries.databinding.FragmentElectronicsBinding
import com.example.onlinegroceries.databinding.FragmentHeaderOfHomeBinding
import com.example.onlinegroceries.databinding.FragmentJewelriesBinding
import com.example.onlinegroceries.databinding.FragmentMenclothingBinding
import com.example.onlinegroceries.databinding.FragmentWomenclothingBinding
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.ui.home.jewelery.JeweleryAdapter
import com.example.onlinegroceries.ui.home.header.ads.HeaderHome
import com.example.onlinegroceries.utility.Constants
import com.example.onlinegroceries.utility.SharedPreferencesHelper

class ParentAdapter(
    private val fragment:Fragment,
    private var items: List<ParentItem>,
    private val listener: OnClick
    ) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {
    private val shared=SharedPreferencesHelper(fragment.requireContext())
    private val headerHome= HeaderHome(fragment)

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
                    FragmentMenclothingBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }
            BEST_SELLING -> {
                BestSellingViewHolder(
                    FragmentElectronicsBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }

            GROCERIES -> {
                GroceriesViewHolder(
                    FragmentJewelriesBinding.inflate(
                        inflate,
                        parent, false
                    )
                )

            }
            else -> {
                BottomOfHomeViewHolder(
                    FragmentWomenclothingBinding.inflate(
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
                val exclusiveAdapter = ProductsAdapter(items[position].data as List<ProductModelItem>)
                {
                    listener.onClickItem(it)
                }
                holder.binding.RecycleExclusive.adapter = exclusiveAdapter
            }
            is BestSellingViewHolder -> {
                val productsAdapter = ProductsAdapter(items[position].data as List<ProductModelItem>)
                {
                    listener.onClickItem(it)
                }
                holder.binding.RecycleBestSelling.adapter = productsAdapter


            }
            is GroceriesViewHolder -> {
                val jeweleryAdapter =
                    JeweleryAdapter(items[position].data as List<ProductModelItem>) {
                        listener.onClickItem(it)
                    }
                holder.binding.RecycleGroceries.adapter = jeweleryAdapter
            }
            is BottomOfHomeViewHolder -> {
                val bottomOfHomeAdapter =
                    JeweleryAdapter(items[position].data as List<ProductModelItem>) {
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

    class ExclusiveViewHolder(val binding: FragmentMenclothingBinding) :
        ParentViewHolder(binding)


    class BestSellingViewHolder(val binding: FragmentElectronicsBinding) :
        ParentViewHolder(binding)


    class GroceriesViewHolder(val binding: FragmentJewelriesBinding) :
        ParentViewHolder(binding)


    class BottomOfHomeViewHolder(val binding: FragmentWomenclothingBinding) :
        ParentViewHolder(binding)


    companion object {
        const val Header = R.layout.fragment_header_of_home
        const val EXCLUSIVE_OFFER = R.layout.fragment_menclothing
        const val BEST_SELLING = R.layout.fragment_electronics
        const val GROCERIES = R.layout.fragment_jewelries
        const val BOTTOM_HOME = R.layout.fragment_womenclothing

    }


}
