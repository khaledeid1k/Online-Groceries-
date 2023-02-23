package com.example.onlinegroceries.ui.profile

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.FragmentProfileBinding


class F_Profile : Fragment() {
    private lateinit var getResult: ActivityResultLauncher<Intent>
    lateinit var binding: FragmentProfileBinding
    var addImage: Boolean = true
    private lateinit var prefsProfile: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hidden toolbar
        activity?.actionBar?.hide()

        // Initialize SharedPreferences
        prefsProfile = requireActivity().getSharedPreferences(
            "profile", MODE_PRIVATE
        )

        resultImageChooser()
        addProfilePhoto()
        submitProfile()
    }

    // click to chose image from gallery
    // the Select Image Button is clicked
    private fun addProfilePhoto() {
        binding.addPhoto.setOnClickListener {
            imageChooser()
        }
    }

    // intent to chose image from gallery
    private fun imageChooser() {
        // Caller
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        getResult.launch(intent)

    }

    //result return from intent of choosing photo from gallery
    private fun resultImageChooser() {
        // Receiver
        getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) {
                    binding.addPhoto.setImageURI(it.data?.data)
                    if (it.data?.data.toString().isNotEmpty()) {
                        addImage = false
                        prefsProfile.edit().putString("image", it.data?.data.toString()).apply()
                    }
                }
            }
    }

    //move to Home Screen after add photo and name
    private fun submitProfile() {
        binding.SubmitProfile.setOnClickListener {
            if (binding.YourName.text?.isNotEmpty() == true) {
                prefsProfile.edit().putString("name", binding.YourName.text.toString()).apply()
                if (!addImage) {
                    findNavController().navigate(R.id.action_profile_to_homeG)
                } else {
                    Toast.makeText(requireActivity(), "Pleas add Profile Image", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireActivity(), "Pleas add Your Name", Toast.LENGTH_SHORT).show()
            }

        }
    }
}


