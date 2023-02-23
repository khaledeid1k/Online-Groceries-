package com.example.onlinegroceries.utility.permession

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.onlinegroceries.ui.singIn.F_PhoneNumber


class Permission(
    var context: Context, var permession: String,
    var fragmentActivity: FragmentActivity
) : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermissionIsGranted() {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                //            findNavController(@IdRes viewId: int)
                context.startActivity(Intent(context, F_PhoneNumber::class.java))
            }
            shouldShowRequestPermissionRationale(fragmentActivity, permession)
            -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                showInContextUI()
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.SEND_SMS
                )
            }
        }


    }


    // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher. You can use either a val, as shown in this snippet,
// or a lateinit var in your onAttach() or onCreate() method.
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                context.startActivity(Intent(context, F_PhoneNumber::class.java))
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }


    //Dialog Explain if user refuse permission for request
    private fun showInContextUI() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Permission needed")
        builder.setMessage("This permission is needed to verifies from your phone number")
        builder.setPositiveButton("OK") { dialog, which ->
        }

        builder.show()

    }


}