package com.example.onlinegroceries.singIn

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinegroceries.databinding.ActivityProfileBinding
import com.example.onlinegroceries.ui.HomeScreen.HomeScreen
import java.net.URI


class Profile : AppCompatActivity() {
    private lateinit var getResult: ActivityResultLauncher<Intent>
    lateinit var binding : ActivityProfileBinding
    var addImage: Boolean =true
    private lateinit var  prefsProfile: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Hidden toolbar
        supportActionBar?.hide()
        prefsProfile= getSharedPreferences("profile", MODE_PRIVATE)
        resultImageChooser()
        addProfilePhoto()
        submitProfile()
    }
    // click to chose image from gallery
    // the Select Image Button is clicked
    private fun addProfilePhoto(){
        binding.addPhoto.setOnClickListener{
            imageChooser()
        }
    }
    // this function is triggered when

    // intent to chose image from gallery
    private fun imageChooser() {
        // Caller
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        getResult.launch(intent)

      }
    //result return from intent of choosing photo from gallery
    private fun resultImageChooser(){
        // Receiver
         getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == Activity.RESULT_OK) {
                    binding.addPhoto.setImageURI(it.data?.data)
                    if (it.data?.data.toString().isNotEmpty()) {
                        addImage = false
                        prefsProfile.edit().putString("image", it.data?.data.toString()).apply()
                    }
                }
            }
    }



    fun submitProfile(){
        binding.SubmitProfile.setOnClickListener{
            if(binding.YourName.text?.isNotEmpty() == true){
                prefsProfile.edit().putString("name",binding.YourName.text.toString()).apply()
                if(!addImage){

                    startActivity(Intent(this,HomeScreen::class.java))
                }else{
                    Toast.makeText(this, "Pleas add Profile Image", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Pleas add Your Name", Toast.LENGTH_SHORT).show()
            }

        }
    }
}


