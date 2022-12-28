package com.example.onlinegroceries.singIn

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.onlinegroceries.ui.selectLocation.SelectLocation
import com.example.onlinegroceries.databinding.ActivitySingInBinding
import com.example.onlinegroceries.singIn.permession.Permission
import com.example.onlinegroceries.singIn.phoneNumber.Number
import com.google.firebase.auth.FirebaseAuth


class SingIn : AppCompatActivity() {
    private lateinit var binding: ActivitySingInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var permission: Permission


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Hidden toolbar
        supportActionBar?.hide()
        moveToWriteYouPhoneNumber()

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Initialize Permission class
        permissionForSendMessage()

    }


    @RequiresApi(Build.VERSION_CODES.M)
   private fun moveToWriteYouPhoneNumber() {
       binding.textInputLayout.setOnClickListener {
           permission.checkPermissionIsGranted()

       }
   }
    //permission For Send Message
    private fun permissionForSendMessage(){
        permission=Permission(this,Manifest.permission.SEND_SMS,this)
    }

    // get in user direct to main app if he sign in
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser!=null){
            startActivity(Intent(this, SelectLocation::class.java))
        }

    }


    }
