package com.example.freedidapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.freedidapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()


        var progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Processing......")



        binding.createAcc.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register)
        }
        binding.createAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register)
        }


        binding.login.setOnClickListener {

            if (binding.pswEt.text.toString()
                    .isEmpty()
            ) {
                binding.password.error = "Input A Password"
                binding.password.requestFocus()
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()) {
                binding.email.error = "Invalid Email"
                binding.email.requestFocus()

            } else {
                auth.signInWithEmailAndPassword(
                    binding.emailEt.text.toString(),
                    binding.pswEt.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                         showSnackbar("Welcome Back!!!")

                            val intent = Intent(requireContext(), ActivityHolder::class.java)
                            activity?.startActivity(intent)
                        } else {
                         showSnackbar("Cant Login now")
                        }
                    }


            }
        }


        return view
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

    private fun reload() {
        val user = Firebase.auth.currentUser
        if (user != null) {
            val intent = Intent(requireContext(), ActivityHolder::class.java)
            activity?.startActivity(intent)

        }else{

        }
        // User is signed in
    }
    private fun showSnackbar(message: String) {
        val rootView = binding.root
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }

}




