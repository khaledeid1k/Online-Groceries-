package com.example.onlinegroceries.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>  : AppCompatActivity() {
    abstract val bindingInflater: (Int) -> VB

}