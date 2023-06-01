package com.example.freedidapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.freedidapp.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Register : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegisterBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment]

        auth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("BusinessUsers")

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        val uid = auth.currentUser?.uid
        auth = FirebaseAuth.getInstance()
        var progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Loading Accounts")
        binding.logIn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_register_to_login)
        }
        binding.login.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_register_to_login)
        }


        binding.createAccount.setOnClickListener {

            if (binding.pswEt.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(requireContext(), "Fields Must Not Be Empty", Toast.LENGTH_SHORT)
                    .show()
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()) {
                binding.email.error = "Invalid Email Address"
                binding.email.requestFocus()
            } else {
                auth.createUserWithEmailAndPassword(
                    binding.emailEt.text.toString(),
                    binding.pswEt.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showSnackbar("Account Registered")
                            Navigation.findNavController(view)
                                .navigate(R.id.action_register_to_businessProfile)
                        } else {
                            showSnackbar("Cant Register You now. Try Again")
                            progressDialog.dismiss()
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