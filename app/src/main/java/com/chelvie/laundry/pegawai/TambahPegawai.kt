package com.chelvie.laundry.pegawai

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chelvie.laundry.R
import com.chelvie.modeldata.model_pegawai
import com.chelvie.modeldata.model_pelanggan
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

class TambahPegawai : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var tvTitle: TextView
    lateinit var tvNama: TextView
    lateinit var etgaris2:EditText
    lateinit var etgaris1: EditText
    lateinit var tvAlamat: EditText
    lateinit var tvNo: EditText
    lateinit var etgaris3:EditText
    lateinit var btSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
        init()
//        getData()
        btSimpan.setOnClickListener{
            cekValidasi()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun simpan(){
        val pelangganBaru = myRef.push()
        val pelangganId = pelangganBaru.key
        val data = model_pegawai(
            pelangganId.toString(),
            tvNama.text.toString(),
            tvAlamat.text.toString(),
            tvNo.text.toString(),
        )

        val updateData = mutableMapOf<String, Any>()
        updateData["namaPegawai"] = data.namaPegawai.toString()
        pelangganBaru.setValue(data).addOnSuccessListener {
            Toast.makeText(this,this.getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            finish()
        }
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }

    }

    fun init(){
        tvTitle = findViewById(R.id.tvTitle)
        tvNama = findViewById(R.id.tvNama)
        tvAlamat = findViewById(R.id.tvAlamat)
        tvNo = findViewById(R.id.tvNo)
        btSimpan = findViewById(R.id.btsimpan)


    }

    fun cekValidasi(){
        val nama = tvNama.text.toString()
        val alamat = tvAlamat.text.toString()
        val noHP = tvNo.text.toString()
        //Validasi data
        if (nama.isEmpty()){
            tvNama.error=this.getString(R.string.nama_pegawai)
            Toast.makeText(this@TambahPegawai , this.getString(R.string.nama_pegawai), Toast.LENGTH_SHORT).show()
            tvNama.requestFocus()
            return
        }
        if (alamat.isEmpty()){
            tvAlamat.error=this.getString(R.string.alamat)
            Toast.makeText(this@TambahPegawai , this.getString(R.string.alamat),  Toast.LENGTH_SHORT).show()
            tvAlamat.requestFocus()
            return
        }
        if (noHP.isEmpty()){
            tvNo.error=this.getString(R.string.no_hp)
            Toast.makeText(this@TambahPegawai , this.getString(R.string.no_hp), Toast.LENGTH_SHORT).show()
            tvNo.requestFocus()
            return
        }
        if (btSimpan.text.equals("Simpan")){
            simpan()
        }else if (btSimpan.text.equals("Sunting")){
            simpan()
            tvNama.requestFocus()
            btSimpan.text="perbarui"
        }else if (btSimpan.text.equals("perbarui")){

        }

    }
}