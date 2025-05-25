package com.chelvie.laundry

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class activity_transaksi : AppCompatActivity() {
    private lateinit var btpilih_layanan: Button
            private lateinit var btlayanan_tambahan: Button
            private lateinit var btproses: Button
            private lateinit var tvPelangganNama : TextView
            private lateinit var tvPelangganNoHP : TextView
            private lateinit var tvLayananNama : TextView
            private lateinit var tvLayananHarga : TextView
            private lateinit var tvlayananTambahan: RecyclerView
            private val dataList = mutableLIstOf<ModelTransaksiTambahan>()

    private val pilihPelanggan = 1
    private val pilihLayanan = 2
    private val pilihlayanan_tambahan = 3

    private var idPelanggan: String=""
    private var idCabang: String=""
    private var namaPelanggan: String=""
    private var noHP: String=""
    private var idLayanan: String=""
    private var namaLayanan: String=""
    private var hargaLayanan: String=""
    private var idPegawai: String=""
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaksi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}