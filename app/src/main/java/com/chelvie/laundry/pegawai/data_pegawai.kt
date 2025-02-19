package com.chelvie.laundry.pegawai

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chelvie.laundry.R
import com.chelvie.laundry.pelanggan.TambahPelanggan
import com.google.android.material.floatingactionbutton.FloatingActionButton

class data_pegawai : AppCompatActivity() {
    lateinit var tambah : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pegawai)

        tambah = findViewById(R.id.fabDATA_PEGAWAI_Tambah)
        tambah.setOnClickListener{
            val intent = Intent(this, TambahPegawai::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}