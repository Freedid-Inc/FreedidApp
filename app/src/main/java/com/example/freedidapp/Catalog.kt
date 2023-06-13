package com.example.freedidapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.example.freedidapp.data.CatalogPhoto
import com.example.freedidapp.data.DataImage
import com.example.freedidapp.data.FreedidCatalog
import com.example.freedidapp.data.FreedidUsers
import com.example.freedidapp.databinding.FragmentCatalogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URL


class Catalog : BottomSheetDialogFragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private var uri: Uri? = null
    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val view = binding.root
        databaseReference = FirebaseDatabase.getInstance().reference.child("catalog")
        storageReference = FirebaseStorage.getInstance().getReference("catalogImage")
        val uid = Firebase.auth.currentUser?.uid.toString()

        binding.cancel.setOnClickListener {
            dismiss()
        }


        val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.image.setImageURI(it)
            if (it != null) {
                uri = it
            }
        }

        binding.upload.setOnClickListener {

            imagePicker.launch("image/*")
        }





        binding.catalogSaved.setOnClickListener {

            val productName = binding.productNameEt.text.toString()
            val descriptionName = binding.descriptionName.text.toString()
            val priceName = binding.priceName.text.toString()
            val shippingAmount = binding.shippingAmount.text.toString()

            if (binding.productNameEt.text.toString().isEmpty()) {
                binding.productName.error = "Input Field"
                binding.productName.requestFocus()
            }
            if (binding.descriptionName.text.toString().isEmpty()) {
                binding.description.error = "Input Field"
                binding.description.requestFocus()
            }
            if (binding.priceName.text.toString().isEmpty()) {
                binding.price.error = "Input Field"
                binding.price.requestFocus()
            }
            if (binding.shippingAmount.text.toString().isEmpty()) {
                binding.shipping.error = "Input Field"
                binding.shipping.requestFocus()
            } else {
                val catalogUsers =
                    FreedidCatalog(
                        productName,
                        priceName,
                        shippingAmount,
                        descriptionName
                    )
                uri?.let {

                    storageReference.child(uid).putFile(it)

                        .addOnSuccessListener { task ->
                            task!!.metadata!!.reference!!.downloadUrl
                                .addOnSuccessListener { url ->
                                    Toast.makeText(
                                        requireContext(),
                                        "Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    var imageURL = url.toString()

                                    val data = DataImage(imageURL)
                                    databaseReference.child(uid).setValue(catalogUsers)
                                        .addOnCompleteListener {
                                            if (it.isSuccessful) {
                                                Toast.makeText(
                                                    context,
                                                    "Successful",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                dismiss()
                                            }
                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                context,
                                                "Unsuccessful",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                            dismiss()
                                        }

                                }
                        }
                }

            }
        }



            return view
        }


    }