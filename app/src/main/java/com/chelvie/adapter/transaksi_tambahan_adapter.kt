package com.chelvie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import transaksi.model_layanan_tambahan

class transaksi_tambahan_adapter(
    private val context: Context, // Ganti activity_transaksi dengan Context
    private val listTambahan: MutableList<model_layanan_tambahan>, // Ganti MutableList<Any> dengan tipe yang benar
    private val tvKosong: TextView
) : RecyclerView.Adapter<transaksi_tambahan_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item_tambahan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tambahan = listTambahan[position]
        holder.bind(tambahan)
    }

    override fun getItemCount(): Int = listTambahan.size // Perbaikan: return Int dan implementasi yang benar

    fun addSelectedItem(tambahan: model_layanan_tambahan) {
        // Cek apakah item sudah ada berdasarkan ID
        val existingIndex = listTambahan.indexOfFirst { it.id == tambahan.id }

        if (existingIndex == -1) {
            // Tambah item baru jika belum ada
            listTambahan.add(tambahan)
            notifyItemInserted(listTambahan.size - 1)
        } else {
            // Update item yang sudah ada
            listTambahan[existingIndex] = tambahan
            notifyItemChanged(existingIndex)
        }

        updateEmptyView()
    }

    fun getSelectedItems(): List<model_layanan_tambahan> { // Perbaikan: return type yang benar
        return listTambahan.toList()
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < listTambahan.size) {
            listTambahan.removeAt(position)
            notifyItemRemoved(position)
            updateEmptyView()
        }
    }

    fun clearAll() {
        listTambahan.clear()
        notifyDataSetChanged()
        updateEmptyView()
    }

    private fun updateEmptyView() {
        if (listTambahan.isEmpty()) {
            tvKosong.visibility = View.VISIBLE
        } else {
            tvKosong.visibility = View.GONE
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        private val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
        private val btnHapus: TextView = itemView.findViewById(R.id.btn_card_hapus)

        fun bind(tambahan: model_layanan_tambahan) {
            tvNama.text = tambahan.namaLayananTambahan
            tvHarga.text = tambahan.hargaLayananTambahan.toString()

            // Handle hapus item
            btnHapus.setOnClickListener {
                removeItem(adapterPosition)
            }

            // Optional: Handle click pada item
            itemView.setOnClickListener {
                // Bisa ditambahkan aksi ketika item diklik
            }
        }
    }
}