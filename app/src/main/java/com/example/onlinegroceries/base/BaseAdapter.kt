package com.example.onlinegroceries.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.BR
import com.example.onlinegroceries.ui.DiffUtils
import kotlinx.parcelize.Parcelize

abstract class  BaseAdapter<T>(
    private var items:List<T>,
    private var listener :BaseInteractionInter

) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    fun setItems(newItem:List<T>){
        val diffUtil = DiffUtil.calculateDiff(DiffUtils(items, newItem))
        items = newItem
        diffUtil.dispatchUpdatesTo(this)
    }
    fun getItems()=items
    // covert xml to kotlin
    abstract val layoutId : Int
    // يتم استدعاء الداله  بعدد ال views الي ممكن تتحط في الشاشة وبعد كده مش هتستدعي تاني
    // وهي بتعمل inflate للعنصر الي ال ViewHolder مسئول عنه
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate
                (
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    // دي بتعمل اعاده استخدام لل viewHolder وبتمرر بيانات جديده
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when(holder){
            is ItemViewHolder->{
                holder.bindingItem.apply {
                    setVariable(BR.item,currentItem)
                    setVariable(BR.listener,listener)

                }

            }
        }
    }

    override fun getItemCount()= items.size


    // connect code to xml
    // هو حامل لل item الي راح احطه في RecyclerView
    // هوالمسؤال عن ال item الي عايز اعرضه داخل ال RecycleView
     abstract class BaseViewHolder(val binding: ViewDataBinding)
        : RecyclerView.ViewHolder(
        binding.root) {

    }
    class ItemViewHolder(val bindingItem: ViewDataBinding) :
        BaseViewHolder(bindingItem)



}
interface BaseInteractionInter
