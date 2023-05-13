package com.example.onlinegroceries.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class  BaseAdapter<VB:ViewDataBinding,T>(
    private var items:List<T>,
    private var onSelect: (T) -> Unit,
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VB>>() {
    fun setItems(newItem:List<T>){
        val diffUtil = DiffUtil.calculateDiff(DiffUtils(items, newItem))
        items = newItem
        diffUtil.dispatchUpdatesTo(this)
    }
    fun getItems()=items
    // covert xml to kotlin
    abstract fun bindItem(ViewDataBinding: VB, data: T)
    abstract val layoutId : Int
    // يتم استدعاء الداله  بعدد ال views الي ممكن تتحط في الشاشة وبعد كده مش هتستدعي تاني
    // وهي بتعمل inflate للعنصر الي ال ViewHolder مسئول عنه
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
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
    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindItem(holder.binding, items[position])
            // onSelect(items[position])
    }

    override fun getItemCount()= items.size


    // connect code to xml
    // هو حامل لل item الي راح احطه في RecyclerView
    // هوالمسؤال عن ال item الي عايز اعرضه داخل ال RecycleView
     class BaseViewHolder<VB:ViewDataBinding>(val binding: VB)
        : RecyclerView.ViewHolder(
        binding.root)
    {


//        // bind your view here
//        itemView.rootView.setOnClickListener {
//        onSelect(T)
//    }

        }
    // to check if list change or not and update list

    inner class DiffUtils<T>(private val oldList: List<T>, private val newList: List<T>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
    }

//    fun updateData(newList : ProductResponse){
//        val diffResult= DiffUtil.calculateDiff(MyDiffUtil(productsList,newList))
//        productsList=newList
//        diffResult.dispatchUpdatesTo(this)
//    }

}
