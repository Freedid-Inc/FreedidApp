package com.example.freedidapp.utis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freedidapp.databinding.UserPresentationBinding

class FreedidAdapter(private val list: MutableList<ToolData>) :
    RecyclerView.Adapter<FreedidAdapter.FreedidViewHolder>() {

private var listener : FreedidAdapterClick? = null
    fun setListener(listener:FreedidAdapterClick){
        this.listener = listener
    }
    inner class FreedidViewHolder(val binding: UserPresentationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreedidViewHolder {

        val binding =
            UserPresentationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FreedidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FreedidViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.productNameDb.text = this.users.productName
                binding.shippingPriceDb.text = this.users.shoppingPrice
                binding.productPriceDb.text = this.users.price
                binding.productDescriptionDb.text = this.userId



                binding.editTool.setOnClickListener {

                    listener?.onEditTaskBtnClick(this)

                }

            }

        }

    }

    override fun getItemCount(): Int {

        return list.size

    }

    interface FreedidAdapterClick {
        fun onEditTaskBtnClick(toolData: ToolData)
    }

}