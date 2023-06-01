package com.example.freedidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.freedidapp.data.FreedidUsers
import com.example.freedidapp.databinding.FragmentSearchBinding


class Search : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root








        return view
    }

    private fun getRegisteredUsers(): List<FreedidUsers> {

        val userList = mutableListOf<FreedidUsers>()
        // add registered users to the list
        userList.add(FreedidUsers("John Doe",  "john.doe@example.com"))
        userList.add(FreedidUsers("John Doe",  "john.doe@example.com"))
        userList.add(FreedidUsers("John Doe",  "john.doe@example.com"))
        userList.add(FreedidUsers("John Doe",  "john.doe@example.com"))
        return userList
    }

}