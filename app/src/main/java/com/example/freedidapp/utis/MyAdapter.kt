package com.example.freedidapp.utis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freedidapp.R
import com.example.freedidapp.data.FreedidCatalog

class MyAdapter(private val userList: ArrayList<FreedidCatalog>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_presentation, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]

        holder.productName.text = currentItem.productName
        holder.shippingAmount.text = currentItem.shoppingPrice
        holder.productPrice.text = currentItem.price
        holder.description.text = currentItem.description



    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName: TextView = itemView.findViewById(R.id.product_name_db)
        val shippingAmount: TextView = itemView.findViewById(R.id.shipping_price_db)
        val productPrice: TextView = itemView.findViewById(R.id.product_price_db)
        val description : TextView = itemView.findViewById(R.id.product_description_db)

    }

}