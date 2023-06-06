package com.example.freedidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.freedidapp.databinding.FragmentSaveCashBinding


class SaveCash : Fragment() {
    private var _binding: FragmentSaveCashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSaveCashBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.proceed.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_saveCash2_to_paymneyOption)
        }



        return view
    }


}