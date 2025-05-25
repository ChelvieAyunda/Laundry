package com.chelvie.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.modeldata.model_pelanggan
import com.google.firebase.database.DatabaseReference

class PilihPelangganAdapter (private val pelangganList: ArrayList<model_pelanggan>) : RecyclerView.Adapter<PilihPelangganAdapter.Viewholder>() {
    lateinit var appContext: Context
    lateinit var databaseReference: DatabaseReference

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_cari_data_pelanggan, parent, false)
        appContext = parent.context
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val nomor = position + 1
        val item = pelangganList[position]
        holder.tvid.text = "[$nomor]"
        holder.tvNama.text = item.namaPelanggan
        holder.tvAlamat.text = "Alamat= ${item.alamatPelanggan}"
        holder.tvNoHP.text = "No Hp= ${item.noHPPelanggan}"
        holder.cvCard.setOnClickListener {
            val intent = Intent()
            intent.putExtra("idPelanggan", item.idPelanggan)
            intent.putExtra("nama", item.namaPelanggan)
            intent.putExtra("noHP", item.noHPPelanggan)
            (appContext as Activity).setResult(Activity.RESULT_OK, intent)
            (appContext as Activity).finish()
        }
    }

    override fun getItemCount(): Int {
        return pelangganList.size
    }

    class Viewholder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cvCard = itemView.findViewById<View>(R.id.tvPELANGGAN)
        val tvid = itemView.findViewById<TextView>(R.id.PilihPelanggan)
        val tvNama = itemView.findViewById<TextView>(R.id.tvNAMAPELANGGAN)
        val tvAlamat = itemView.findViewById<TextView>(R.id.tvAlamat)
        val tvNoHP = itemView.findViewById<TextView>(R.id.tvNOHP)
    }
}