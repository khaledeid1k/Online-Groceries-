package com.example.onlinegroceries.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class  BaseAdapter<VB:ViewBinding,T:Any>(
    private val list: List<T>,
   // private var onSelect: (T) -> Unit,
) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VB>>() {
    // covert xml to kotlin
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract fun bindItem(viewBinding: VB, data: T)


    // يتم استدعاء الداله  بعدد ال views الي ممكن تتحط في الشاشة وبعد كده مش هتستدعي تاني
    // وهي بتعمل inflate للعنصر الي ال ViewHolder مسئول عنه
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
            bindingInflater(
                LayoutInflater.from(
                    parent.context
                ), parent, false)
        )
    }

    // دي بتعمل اعاده استخدام لل viewHolder وبتمرر بيانات جديده
    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindItem(holder.binding,list[position])
          //    onSelect(list[position])
    }

    override fun getItemCount(): Int {

        return list.size
    }

    // connect code to xml
    // هو حامل لل item الي راح احطه في RecyclerView
    // هوالمسؤال عن ال item الي عايز اعرضه داخل ال RecycleView
    class BaseViewHolder<VB:ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(
        binding.root)
    {


//        // bind your view here
//        itemView.rootView.setOnClickListener {
//        onSelect(T)
//    }


    }


    // to check if list change or not and update list
//    fun updateData(newList : ProductResponse){
//        val diffResult= DiffUtil.calculateDiff(MyDiffUtil(productsList,newList))
//        productsList=newList
//        diffResult.dispatchUpdatesTo(this)
//    }
}