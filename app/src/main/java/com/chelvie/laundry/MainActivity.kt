package com.chelvie.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chelvie.laundry.pegawai.TambahPegawai
import com.chelvie.laundry.pegawai.data_pegawai
import com.chelvie.laundry.pelanggan.DataPelanggan

class MainActivity : AppCompatActivity() {
    lateinit var pelanggan1: ImageView
    lateinit var pegawai: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
        tekan()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        pelanggan1 = findViewById(R.id.pelanggan)
        pegawai = findViewById(R.id.tvLpegawai)
    }

    fun tekan() {
        pegawai.setOnClickListener {
            val intent = Intent(this , data_pegawai::class.java)
            startActivity(intent)
        }
        pelanggan1.setOnClickListener {
            val intent = Intent(this , DataPelanggan::class.java)
            startActivity(intent)
        }
    }
}