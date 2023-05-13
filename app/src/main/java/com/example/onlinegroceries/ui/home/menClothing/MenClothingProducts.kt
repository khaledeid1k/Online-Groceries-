package com.example.onlinegroceries.ui.home.menClothing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.databinding.FragmentMenclothingProductsBinding
import com.example.onlinegroceries.utility.extention.closeFragment

class MenClothingProducts : Fragment() {
    lateinit var binding: FragmentMenclothingProductsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenclothingProductsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()
        // initialize  recyclerView
        recyclerView = binding.RecycleExclusive

        backToHome()
    }

    private fun backToHome(){
        binding.backToHomeFromEX.setOnClickListener {
                      //   parentFragmentManager.findFragmentById(R.id.AHome)
                         closeFragment()
        //              findNavController().navigate(R.id.action_exclusiveOfferProducts_to_FHome)

        }
    }
}

