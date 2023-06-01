package com.example.freedidapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.navigation.Navigation
import com.example.freedidapp.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SplashScreen : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if (auth.currentUser != null) {
                val intent = Intent(requireContext(), ActivityHolder::class.java)
                activity?.startActivity(intent)
            } else {
                Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_login)
            }

        }, 7000)


        return view
    }

/*    override fun onStart() {
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
            Handler().postDelayed({
                val intent = Intent(requireContext(), ActivityHolder::class.java)
                activity?.startActivity(intent)
            }, 7000)
        } else {

        }
        // User is signed in
    }*/


}