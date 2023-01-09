package com.example.onlinegroceries.Network.dI

import android.app.Application
import android.os.UserManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {


        super.onCreate()

    }

}