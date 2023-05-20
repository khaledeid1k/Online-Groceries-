    package com.example.onlinegroceries.ui.home

    import android.os.Bundle
    import android.view.View
    import android.widget.Toast
    import androidx.navigation.fragment.findNavController
    import com.example.onlinegroceries.R
    import com.example.onlinegroceries.base.BaseFragment
    import com.example.onlinegroceries.databinding.FragmentHomeBinding
    import com.example.onlinegroceries.model.ParentItem
    import com.example.onlinegroceries.model.TYPE
    import com.example.onlinegroceries.utility.extention.makeGone
    import com.google.android.material.bottomnavigation.BottomNavigationView
    import java.net.Proxy.Type

    class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), ItemHomeInteractionInter {
        override val viewModelClass = HomeViewModel::class.java
        override val LOG_TAG: String = "Home"
        lateinit var parentAdapter: ParentAdapter
        override fun getLayoutId() = R.layout.fragment_home
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            // Hidden toolbar
            activity?.actionBar?.hide()
            parentAdapter = ParentAdapter(this, mutableListOf(), this)
            binding.PRecyclerView.adapter = parentAdapter

            observeViewModel()
        }

        private fun observeViewModel() {

            viewModel.apply {
                productsList.observe(viewLifecycleOwner) {
                    it?.let {
                        binding.lottieAnimationView.makeGone()
                        parentAdapter = ParentAdapter(this@HomeFragment, it, this@HomeFragment)
                        binding.PRecyclerView.adapter = parentAdapter
                    }
                }

                itemStand.observe(viewLifecycleOwner) {
                    val action = it.getContentIfNotHandled()?.let {// Only proceed if the event has never been handled
                            productModelItem ->
                        HomeFragmentDirections.homeToShowProduct(productModelItem) }
                    if (action != null) {
                        findNavController().navigate(action)
                    }


                }

                itemSleep.observe(viewLifecycleOwner) {
                    val action = it.getContentIfNotHandled()?.let {// Only proceed if the event has never been handled
                            productModelItem ->
                        HomeFragmentDirections.homeToShowProduct(productModelItem) }
                    if (action != null) {
                        findNavController().navigate(action)
                    }
                }
            }
        }


        override fun onClickItem(parentItem: ParentItem) {
            val destinationType = when (parentItem) {
                is ParentItem.ELECTRONICS -> TYPE.ELECTRONICS
                is ParentItem.JEWELRIES -> TYPE.JEWELRIES
                is ParentItem.MEN_CLOTHING -> TYPE.MEN_CLOTHING
                is ParentItem.WOMEN_CLOTHING -> TYPE.WOMEN_CLOTHING
                else -> null
            }

            destinationType?.let { type ->
                val action = HomeFragmentDirections.homeToAllProducts(type)
                findNavController().navigate(action)
            }
        }


    }


