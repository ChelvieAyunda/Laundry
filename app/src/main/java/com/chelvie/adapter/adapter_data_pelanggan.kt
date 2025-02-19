package com.chelvie.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.modeldata.model_pelanggan

class DataPelangganAdapter(
    private val listpelanggan: ArrayList<model_pelanggan>
) : RecyclerView.Adapter<DataPelangganAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listpelanggan[position]
        holder.tvID.text = item.idPelanggan
        holder.tvNama.text = item.namaPelanggan
        holder.tvAlamat.text = item.alamatPelanggan
        holder.tvNoHP.text = item.noHPPelanggan
        holder.tvTerdaftar.text = item.terdaftar
        holder.cvCard.setOnClickListener {

        }
        holder.btHubungi.setOnlicklistener {

        }
        holder.btLihat.setOnlicklistener {

        }
    }

    override fun getItemCount(): Int {
        return listpelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.card_pelanggan)
        val tvID: TextView = itemView.findViewById(R.id.)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvNoHP)
        val idterdaftar: TextView = itemView.findViewById(R.id.idterdaftar)
    }
}