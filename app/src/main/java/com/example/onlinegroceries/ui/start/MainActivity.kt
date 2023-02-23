package com.example.onlinegroceries.ui.start

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chuckerteam.chucker.databinding.ChuckerActivityMainBinding
import com.example.onlinegroceries.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in_container)
        // Hidden toolbar
        supportActionBar?.hide()


    }

    fun lol(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_singIn) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigation= findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            // Hook your navigation controller to bottom navigation view
        bottomNavigation .setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.splashScreen||
                destination.id == R.id.onBoarding) {
                bottomNavigation.visibility = View.GONE
            } else {
                bottomNavigation.visibility = View.VISIBLE
            }
        }
        bottomNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.account->{navController.navigate(R.id.account)
                    true}
                R.id.cart->{navController.navigate(R.id.cart)
                    true}
                R.id.favorite->{navController.navigate(R.id.favorite)
                    true}
                R.id.explore->{navController.navigate(R.id.explore)
                    true}
                R.id.FHome->{navController.navigate(R.id.FHome)
                    true}
                else -> {false}
            }
        }

    }

    override fun onResume() {
        super.onResume()
        lol()
    }
}