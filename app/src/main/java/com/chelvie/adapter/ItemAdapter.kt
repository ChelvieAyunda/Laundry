package com.chelvie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R

class ItemAdapter(private val serviceList: List<LayananTambahan>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Define your data class (should be in a separate file or at the top level)
    data class LayananTambahan(
        val name: String,
        val price: String
    )

    // ViewHolder class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val serviceName: TextView = view.findViewById(R.id.tvAdditionalItemName)
        val servicePrice: TextView = view.findViewById(R.id.tvAdditionalItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_additional, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = serviceList[position]
        holder.serviceName.text = service.name
        holder.servicePrice.text = service.price
    }

    override fun getItemCount(): Int = serviceList.size
}