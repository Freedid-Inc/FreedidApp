package com.example.freedidapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freedidapp.data.FreedidCatalog
import com.example.freedidapp.data.FreedidUsers
import com.example.freedidapp.databinding.FragmentUsersBinding
import com.example.freedidapp.utis.FreedidAdapter
import com.example.freedidapp.utis.MyAdapter
import com.example.freedidapp.utis.ToolData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Users : Fragment(), Catalog.DialogNextBtnClickListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var popUpFragment: Catalog
    private lateinit var userArrayList: ArrayList<FreedidCatalog>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment





        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("catalog")




        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addCatalog.setOnClickListener {
            popUpFragment = Catalog()
            popUpFragment.setListener(this)
            popUpFragment.show(childFragmentManager, "Catalog")

        }

        val sharedPreferences = activity?.getSharedPreferences("freedid", Context.MODE_PRIVATE)
        val businessName = sharedPreferences?.getString("BUSINESSNAME", "").toString()
        val businessLocation = sharedPreferences?.getString("BUSINESSLOCATION", "").toString()
        val businessDelivery = sharedPreferences?.getString("BUSINESSNUMBER", "").toString()

        binding.brandName.text = businessName
        binding.brandLocation.text = businessLocation
        binding.brandNumber.text = businessDelivery


        binding.businessPhoto.setOnClickListener {

        }

        userArrayList = arrayListOf<FreedidCatalog>()
        getUserData()

        return view
    }
    private fun getUserData() {


 binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {


                    for (userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(FreedidCatalog::class.java)
                        userArrayList.add(user!!)

                    }
                    binding.recyclerView.adapter = MyAdapter(userArrayList)

                }


            }

            override fun onCancelled(error: DatabaseError) {


            }


        })


    }


    override fun onSaveTask(
        dialer: FreedidCatalog,
        productEt: TextInputEditText,
        priceEt: TextInputEditText,
        descriptionEt: TextInputEditText,
        shippingEt: TextInputEditText
    ) {

        databaseReference.push().setValue(dialer).addOnCompleteListener {

            if (it.isSuccessful) {
                Toast.makeText(context, "Catalog Created Successfully", Toast.LENGTH_SHORT)
                    .show()

            } else {
                Toast.makeText(context, "Catalog not created", Toast.LENGTH_SHORT).show()
            }

            popUpFragment.dismiss()
        }


    }


}