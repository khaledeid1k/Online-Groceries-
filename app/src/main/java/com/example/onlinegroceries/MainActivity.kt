package com.example.onlinegroceries

import android.os.Bundle
import android.view.FrameMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chuckerteam.chucker.databinding.ChuckerActivityMainBinding
import com.example.onlinegroceries.R




import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


  private lateinit var bottomNavigation : BottomNavigationView
  lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in_container)
        // Hidden toolbar
        supportActionBar?.hide()
        inti()


    }

    private fun inti(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_singIn) as NavHostFragment
         navController = navHostFragment.navController

         bottomNavigation=
            findViewById(R.id.bottomNavigationView)
            // Hook your navigation controller to bottom navigation view
        bottomNavigation .setupWithNavController(navController)



    }
    private fun navigateFromBottomNavigation(){

        // navigate to fragments from bottom navigation
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
                else -> false
            }
        }

    }
    private fun hiddenBottomNavigation(){
        // hidden bottom navigation from splash and Onboard screens
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.splashScreen||
                destination.id == R.id.onBoarding) {
                bottomNavigation.visibility = View.GONE
            } else {
                bottomNavigation.visibility = View.VISIBLE
            }
        }

    }

    override fun onResume() {
        super.onResume()
        navigateFromBottomNavigation()
        hiddenBottomNavigation()

    }


    }
