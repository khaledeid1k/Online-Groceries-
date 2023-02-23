package com.example.onlinegroceries.utility.extention

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController



    fun Fragment.closeFragment() {
     this.findNavController().popBackStack()

}