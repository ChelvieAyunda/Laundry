package com.chelvie.laundry.pelanggan

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
import com.chelvie.modeldata.model_pelanggan
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

class TambahPelanggan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")
    lateinit var tvjudul: TextView
    lateinit var etNama:EditText
    lateinit var etAlamat: EditText
    lateinit var etNoHp: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan: Button

    var idPelanggan:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
        init()
//        getData()
        btSimpan.setOnClickListener{
            cekValidasi()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvTambahPelanggan)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
     }

    fun init(){
        tvjudul = findViewById(R.id.tvTitle)
        etNama = findViewById(R.id.tvNama)
        etAlamat = findViewById(R.id.tvAlamat)
        etNoHp = findViewById(R.id.tvNo)
        etCabang = findViewById(R.id.tvCabang)
        btSimpan = findViewById(R.id.btsimpan)

    }
    fun getData(){
        idPelanggan = intent.getStringExtra("idPelanggan").toString()
        val judul = intent.getStringExtra("judul")
        val nama = intent.getStringExtra("namaPelanggan")
        val alamat = intent.getStringExtra("alamatPelanggan")
        val hp = intent.getStringExtra("noHpPelanggan")
        val cabang = intent.getStringExtra("idCabang")

        tvjudul.text = judul
        etNama.setText(nama)
        etAlamat.setText(alamat)
        etNoHp.setText(hp)
        etCabang.setText(cabang)
        if (!tvjudul.text.equals(this.getString(R.string.tambah_pelanggan))){
            if (judul.equals("Edit Pelanggan")){
                mati()
                btSimpan.text = "Sunting"
            }
        }else{
            hidup()
            etNama.requestFocus()
            btSimpan.text="Simpan"
        }
    }

    fun mati(){
        etNama.isEnabled=false
        etAlamat.isEnabled=false
        etNoHp.isEnabled=false
        etCabang.isEnabled=false
    }

    fun hidup(){
        etNama.isEnabled=true
        etAlamat.isEnabled=true
        etNoHp.isEnabled=true
        etCabang.isEnabled=true
    }

    fun update(){
        val pelangganRef =  database.getReference("pelanggan").child(idPelanggan)
        val data = model_pelanggan(
            idPelanggan,
            etNama.text.toString(),
            etAlamat.text.toString(),
            etNoHp.text.toString(),
            etCabang.text.toString(),

            )

        val updateData = mutableMapOf<String,Any>()
        updateData["namaPelanggan"] = data.namaPelanggan.toString()
        updateData["alamatPelanggan"] = data.alamatPelanggan.toString()
        updateData["noHpPelanggan"] = data.noHPPelanggan.toString()
        updateData["idCabang"] = data.cabang.toString()
        pelangganRef.updateChildren(updateData).addOnSuccessListener {
            Toast.makeText(this@TambahPelanggan,"Data Pelanggan Berhasil Diperbarui",Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@TambahPelanggan,"Data Pelanggan Gagal Diperbarui",Toast.LENGTH_SHORT).show()
        }
    }

    fun cekValidasi(){
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHp.text.toString()
        val cabang = etCabang.text.toString()
        //Validasi data
        if (nama.isEmpty()){
            etNama.error=this.getString(R.string.nama_pelanggan)
            Toast.makeText(this@TambahPelanggan, this.getString(R.string.nama_pelanggan), Toast.LENGTH_SHORT).show()
            etNama.requestFocus()
            return
        }
        if (alamat.isEmpty()){
                etAlamat.error=this.getString(R.string.alamat)
                Toast.makeText(this@TambahPelanggan, this.getString(R.string.alamat),  Toast.LENGTH_SHORT).show()
                etAlamat.requestFocus()
                return
        }
        if (noHP.isEmpty()){
                etNoHp.error=this.getString(R.string.no_hp)
                Toast.makeText(this@TambahPelanggan, this.getString(R.string.no_hp), Toast.LENGTH_SHORT).show()
                etNoHp.requestFocus()
                return
        }
        if (cabang.isEmpty()){
                etCabang.error=this.getString(R.string.cabang)
                Toast.makeText(this@TambahPelanggan, this.getString(R.string.cabang), Toast.LENGTH_SHORT).show()
                etCabang.requestFocus()
                return
        }
        if (btSimpan.text.equals("Simpan")){
            simpan()
        }else if (btSimpan.text.equals("Sunting")){
            hidup()
            etNama.requestFocus()
            btSimpan.text="Perbarui"
        }else if (btSimpan.text.equals("Perbarui")) {
            update()
        }
        }

        fun simpan(){
            val pelangganBaru = myRef.push()
            val pelangganId = pelangganBaru.key
            val data = model_pelanggan(
                pelangganId.toString(),
                etNama.text.toString(),
                etAlamat.text.toString(),
                etNoHp.text.toString(),
                etCabang.text.toString(),
            )
            pelangganBaru.setValue(data).addOnSuccessListener {
                    Toast.makeText(this,this.getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnSuccessListener {
                    Toast.makeText(this,this.getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                    finish()
                }

        }
    }
