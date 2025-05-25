package com.chelvie.laundry.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.laundry.pegawai.TambahPegawai
import com.chelvie.modeldata.model_pegawai
import com.chelvie.modeldata.model_pelanggan
import com.google.firebase.database.DatabaseReference

class AdapterDataPegawai(
    private val listpegawai: ArrayList<model_pegawai>
) : RecyclerView.Adapter<AdapterDataPegawai.ViewHolder>() {
    lateinit var appContext: Context
    lateinit var databaseReference: DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pegawai, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listpegawai[position]
        holder.tvID.text = item.idPegawai
        holder.tvNama.text = item.namaPegawai
        holder.tvAlamat.text = item.alamatPegawai
        holder.tvNoHP.text = item.noHPPegawai
        holder.cvCard.setOnClickListener{
            val  intent = Intent(appContext, TambahPegawai::class.java)
            intent.putExtra("judul","Edit Pelanggan")
            intent.putExtra("idPegawai",item.idPegawai)
            intent.putExtra("namaPegawai",item.namaPegawai)
            intent.putExtra("noHpPegawai",item.noHPPegawai)
            intent.putExtra("alamatPegawai",item.alamatPegawai)
            appContext.startActivity(intent)

        }
        holder.btHubungi.setOnClickListener() {

        }
        holder.btLihat.setOnClickListener() {

        }
    }

    override fun getItemCount(): Int {
        return listpegawai.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.cvPegawai)
        val tvID: TextView = itemView.findViewById(R.id.idPegawai)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvNo)
        val btHubungi: Button = itemView.findViewById(R.id.btHubungi)
        val btLihat: Button = itemView.findViewById(R.id.btLihat)
    }}










