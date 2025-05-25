package com.chelvie.laundry

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.DatabaseReference

class cari_pelanggan : AppCompatActivity() {
    val database = firebaseDatabase.getInstance()
    val myReference = database.getReference ( "pelanggan")
    lateinit var cariPelanggan: RecyclerView
    lateinit var pelangganList: ArrayList<ModelPelanggan>
    lateinit var tvKosong: TextView
    private lateinit var searchView: SearchView: androidx.appcoopat.widget.searchView
            private lateinit var sharedPreferences: SharedPreferences
            var idCabang: String =""
}

override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_cari_pelanggan)
    SharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
    init()
    idCabang = sharedPref.getString( "idCabang", null).toString()
    val layoutManager = LinearLayoutManager(this)
    layoutManager.reverseLayout = true
    layoutManager.stockFromEnd = true
    rvPilihPelanggan
}