package com.chelvie.laundry.pelanggan

import android.content.Intent
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.laundry.adapter.adapter_data_pelanggan
import com.chelvie.modeldata.model_pelanggan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataPelanggan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")
    lateinit var rvDATA_PELANGGAN: RecyclerView
   // lateinit var fabDATA_PENGGUNA_TAMBAH:FloatingActionButton
    lateinit var pelangganList:ArrayList<model_pelanggan>
    lateinit var tambah : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pelanggan)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rvDATA_PELANGGAN.layoutManager=layoutManager
        rvDATA_PELANGGAN.setHasFixedSize(true)
        pelangganList = arrayListOf<model_pelanggan>()
        getData()


        tambah = findViewById(R.id.fabDATA_PENGGUNA_Tambah)
        tambah.setOnClickListener{
                val intent = Intent(this, TambahPelanggan::class.java)
                intent.putExtra("judul", this.getString(R.string.nama_pelanggan))
                intent.putExtra("idPelanggan","")
                intent.putExtra("namaPelanggan","")
                intent.putExtra("noHpPelanggan","")
                intent.putExtra("alamatPelanggan","")
                intent.putExtra("idCabang","")
                startActivity(intent)
            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init(){
        rvDATA_PELANGGAN = findViewById(R.id.rvDATA_PELANGGAN)
        tambah= findViewById(R.id.fabDATA_PENGGUNA_Tambah)
    }

    fun getData(){
        val query = myRef.orderByChild("idPelanggan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if (snapshot.exists()){
                    pelangganList.clear()
                    for (dataSnapShot in snapshot.children){
                        val pegawai = dataSnapShot.getValue(model_pelanggan::class.java)
                        pelangganList.add(pegawai!!)
                        }
                    val adapter = adapter_data_pelanggan(pelangganList)
                    rvDATA_PELANGGAN.adapter = adapter
                    adapter.notifyDataSetChanged()
                    }
                }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPelanggan, error.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}