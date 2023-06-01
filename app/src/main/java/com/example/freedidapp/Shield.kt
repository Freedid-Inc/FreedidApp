package com.example.freedidapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.freedidapp.databinding.FragmentShieldBinding


class Shield : Fragment() {
    private var _binding: FragmentShieldBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentShieldBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.usersPhoto.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_shield_to_users)

        }

        val sharedPreferences = activity?.getSharedPreferences("freedid", Context.MODE_PRIVATE)
        val businessName = sharedPreferences?.getString("BUSINESSNAME", "").toString()


        binding.businessName.text = businessName


        return view
    }


}