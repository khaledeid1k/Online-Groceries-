package com.example.onlinegroceries.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.onlinegroceries.BR
import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.base.BaseInteractionInter
import com.example.onlinegroceries.databinding.FragmentHeaderOfHomeBinding
import com.example.onlinegroceries.databinding.FragmentItemHomeBinding
import com.example.onlinegroceries.model.ParentItem
import com.example.onlinegroceries.ui.home.header.ads.HeaderHome
import com.example.onlinegroceries.utility.Constants
import com.example.onlinegroceries.utility.SharedPreferencesHelper

class ParentAdapter(
    private val fragment: Fragment,
    private var items: List<ParentItem>,
    private val listenerParent: ItemHomeInteractionInter
) : BaseAdapter<ParentItem>(items, listenerParent) {


    override val layoutId: Int = 0

    private val shared = SharedPreferencesHelper(fragment.requireContext())
    private val headerHome = HeaderHome(fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> {
                ItemViewHolder(
                    FragmentHeaderOfHomeBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }

            ITEM_HOME -> {
                ItemViewHolder(
                    FragmentItemHomeBinding.inflate(
                        inflate,
                        parent, false
                    )
                )
            }



            else -> throw java.lang.ClassCastException("Unknown view type: $viewType")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ParentItem.HEADER -> HEADER
            else -> ITEM_HOME
        }

    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when (holder.binding) {
            is FragmentHeaderOfHomeBinding -> {
                holder.binding.userNameTextView.text = shared.get(Constants.prefsName)
                Glide.with(fragment).load(shared.get(Constants.prefsImage)).into(
                    holder.binding.userImageImageView)
                headerHome.imageSlider(holder.binding)
                headerHome.moveToSearchScreen(holder.binding)
            }
            is FragmentItemHomeBinding -> {
                holder.binding.apply {
                    setVariable(BR.item, currentItem)
                    setVariable(BR.listener, listenerParent)
                }

            }

        }

    }
    companion object {
        const val HEADER = R.layout.fragment_header_of_home
        const val ITEM_HOME = R.layout.fragment_item_home
    }





}
interface ItemHomeInteractionInter : BaseInteractionInter {

    fun onClickItem(parentItem: ParentItem)
}

