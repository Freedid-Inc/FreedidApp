package com.example.freedidapp

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.freedidapp.data.FreedidUsers
import com.example.freedidapp.databinding.FragmentBusinessProfileBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class BusinessProfile : Fragment() {
    private var _binding: FragmentBusinessProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        storageReference = FirebaseStorage.getInstance().getReference("images")
        val uid = Firebase.auth.currentUser?.uid.toString()



        _binding = FragmentBusinessProfileBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.submit.setOnClickListener {

            if (binding.brandNameEt.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Input Brand Name", Toast.LENGTH_SHORT).show()
                binding.brandName.requestFocus()
            }

            if (binding.aboutEt.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Input About Your Business", Toast.LENGTH_SHORT)
                    .show()
                binding.about.requestFocus()
            }
            if (!Patterns.PHONE.matcher(binding.numberEt.text.toString()).matches()) {
                Toast.makeText(requireContext(), "Input Your Phone Number", Toast.LENGTH_SHORT)
                    .show()
            }
            if (binding.locationEt.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Input Your Location", Toast.LENGTH_SHORT).show()
            } else {

                                    val sharedPreferences =
                                        activity?.getSharedPreferences(
                                            "freedid",
                                            Context.MODE_PRIVATE
                                        )
                                    val editor = sharedPreferences?.edit()
                                    editor?.putString(
                                        "BUSINESSNAME",
                                        binding.brandNameEt.text.toString()
                                    )
                                    editor?.putString(
                                        "BUSINESSLOCATION",
                                        binding.locationEt.text.toString()
                                    )
                                    editor?.putString(
                                        "BUSINESSINFO",
                                        binding.aboutEt.text.toString()
                                    )
                                    editor?.putString(
                                        "BUSINESSNUMBER",
                                        binding.numberEt.text.toString()
                                    )

                                    editor?.putString(
                                        "BUSINESSTYPE",
                                        binding.categoryEt.text.toString()
                                    )
                                    editor?.apply()


                                    val businessName = binding.brandNameEt.text.toString()
                                    val businessNumber = binding.numberEt.text.toString()
                                    val businessInfo = binding.aboutEt.text.toString()
                                    val businessLocation = binding.locationEt.text.toString()


                                    val users: FreedidUsers = FreedidUsers(
                                        businessName,
                                        businessNumber,
                                        businessInfo,
                                        businessLocation,

                                    )

                                    databaseReference.child(uid).setValue(users)
                                        .addOnCompleteListener {
                                            if (it.isSuccessful) {
                                                showSnackbar("Business Account Created Successfully")
                                               Navigation.findNavController(view).navigate(R.id.action_businessProfile_to_fragmentBusinessLoggo)
                                            }
                                        }.addOnFailureListener {

                                            showSnackbar("Cant Create Your Business Account")

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