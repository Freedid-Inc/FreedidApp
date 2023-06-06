package com.example.freedidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.freedidapp.data.FreedidCatalog
import com.example.freedidapp.databinding.FragmentCatalogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText


class Catalog : BottomSheetDialogFragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener : DialogNextBtnClickListener

    fun setListener(listener: DialogNextBtnClickListener){
        this.listener = listener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.cancel.setOnClickListener {
            dismiss()
        }


        binding.catalogSaved.setOnClickListener {


            val productName = binding.productNameEt.text.toString()
            val descriptionName = binding.descriptionName.text.toString()
            val priceName = binding.priceName.text.toString()
            val shippingAmount = binding.shippingAmount.text.toString()

            val catalogUsers = FreedidCatalog(productName,priceName,shippingAmount,descriptionName)

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

                listener.onSaveTask(catalogUsers,binding.productNameEt,binding.priceName,binding.descriptionName,binding.shippingAmount)
            }


        }


        return view
    }

    interface DialogNextBtnClickListener {
        fun onSaveTask(
            dialer: FreedidCatalog, productEt: TextInputEditText,
            priceEt: TextInputEditText, descriptionEt: TextInputEditText, shippingEt: TextInputEditText )
    }
}