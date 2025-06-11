package com.chelvie.laundry.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.laundry.pelanggan.TambahPelanggan
import com.chelvie.modeldata.model_pelanggan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class adapter_data_pelanggan(private val listpelanggan: ArrayList<model_pelanggan>
) : RecyclerView.Adapter<adapter_data_pelanggan.ViewHolder>() {

    lateinit var appContext: Context
    lateinit var databaseReference: DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
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
        holder.cabang.text = item.cabang
        holder.cvCard.setOnClickListener {
            val intent = Intent(appContext, TambahPelanggan::class.java)
            intent.putExtra("judul","Edit Pelanggan")
            intent.putExtra("idPelanggan",item.idPelanggan)
            intent.putExtra("namaPelanggan",item.namaPelanggan)
            intent.putExtra("noHpPelanggan",item.noHPPelanggan)
            intent.putExtra("alamatPelanggan",item.alamatPelanggan)
            intent.putExtra("cabang", item.cabang)
            appContext.startActivity(intent)
        }
        holder.btHubungi.setOnClickListener() {

        }
        holder.btLihat.setOnClickListener() {

        }
    }

    override fun getItemCount(): Int {
        return listpelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.card_pelanggan)
        val tvID: TextView = itemView.findViewById(R.id.idpelanggan)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvNo)
        val cabang: TextView = itemView.findViewById(R.id.tvCabang)
        val btHubungi: Button = itemView.findViewById(R.id.btHubungi)
        val btLihat: Button = itemView.findViewById(R.id.btLihat)
    }
}