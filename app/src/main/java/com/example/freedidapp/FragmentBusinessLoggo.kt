package com.example.freedidapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.freedidapp.databinding.FragmentBusinessLoggoBinding
import com.example.freedidapp.data.DataImage
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class FragmentBusinessLoggo : Fragment() {
    private var _binding: FragmentBusinessLoggoBinding? = null
    private val binding get() = _binding!!
    private lateinit var storageReference: StorageReference
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private var uri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databaseReference = FirebaseDatabase.getInstance().getReference("usersImages")
        storageReference = FirebaseStorage.getInstance().getReference("images")
        auth = FirebaseAuth.getInstance()

        val uid = Firebase.auth.currentUser?.uid.toString()

        _binding = FragmentBusinessLoggoBinding.inflate(inflater, container, false)
        val view = binding.root

        val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.businessLoggo.setImageURI(it)

            if (it != null) {
                uri = it
            }

        }

        binding.pick.setOnClickListener {
            imagePicker.launch("image/*")

        }

        binding.save.setOnClickListener {

            uri?.let {

                storageReference.child(uid).putFile(it)

                    .addOnSuccessListener { task ->
                        task!!.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener { url ->
                                showSnackbar("Image Uploaded Successfully")
                                var imageURL = url.toString()

                                val data = DataImage(imageURL)
                                val intent = Intent(requireContext(), ActivityHolder::class.java)
                                activity?.startActivity(intent)
                                databaseReference.child(uid).setValue(data)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "Successful",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }.addOnFailureListener {
                                        Toast.makeText(context, "Unsuccessful", Toast.LENGTH_SHORT)
                                            .show()
                                    }

                            }
                    }
            }
        }
        return view
    }

    private fun showSnackbar(message: String) {
        val rootView = binding.root
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }

}