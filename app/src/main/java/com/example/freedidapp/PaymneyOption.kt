package com.example.freedidapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.freedidapp.databinding.FragmentPaymneyOptionBinding

class PaymneyOption : Fragment() {
    private var _binding: FragmentPaymneyOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymneyOptionBinding.inflate(inflater, container, false)
        val view = binding.root


        val sharedPreferences = activity?.getSharedPreferences("FreedidSave", Context.MODE_PRIVATE)

        val amountSave = sharedPreferences?.getString("AmountSave", "").toString()


        binding.btnPay.setOnClickListener {

            binding.btnPay.text = "Pay$amountSave"


        }




        return view
    }


}