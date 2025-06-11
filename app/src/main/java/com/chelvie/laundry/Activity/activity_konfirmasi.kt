package com.chelvie.laundry

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView

class activity_konfirmasi : Activity() {

    // Declare UI components
    private lateinit var tvName: TextView
    private lateinit var tvNoHP: TextView
    private lateinit var tvKeterangan: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvTambahan: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvHargaTotal: TextView
    private lateinit var btBatal: Button
    private lateinit var btPembayaran: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_data) // Make sure this matches your XML file name

        // Initialize views
        initializeViews()

        // Set click listeners
        setupClickListeners()

        // You can set data here, either from intent or other sources
        setData()
    }

    private fun initializeViews() {
        tvName = findViewById(R.id.tvName)
        tvNoHP = findViewById(R.id.tvNOHP)
        tvKeterangan = findViewById(R.id.tvKeterangan)
        tvHarga = findViewById(R.id.tvharga)
        tvTambahan = findViewById(R.id.tvTambahan)
        tvTotal = findViewById(R.id.tvTOTAL)
        tvHargaTotal = findViewById(R.id.tvHARGA)
        btBatal = findViewById(R.id.btBatal)
        btPembayaran = findViewById(R.id.btPembayaran)
    }

    private fun setupClickListeners() {
        btBatal.setOnClickListener {
            // Handle cancel button click
            finish() // Close the activity
        }

        btPembayaran.setOnClickListener {
            // Handle payment button click
            // You might want to start a new payment activity here
            // Example: startActivity(Intent(this, PaymentActivity::class.java))
        }
    }

    private fun setData() {
        // Example data - in a real app, you would get this from intent or database
        tvName.text = "Najwa"
        tvNoHP.text = "08876256431"
        tvKeterangan.text = "Cuci Setrika 3kg"
        tvHarga.text = "Rp30.000"
        tvHargaTotal.text = "Rp50.000,00"

        // You could add additional services here if needed
    }
}