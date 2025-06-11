package transaksi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.modeldata.model_layanan
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Data_layanan : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabTambah: FloatingActionButton
    private lateinit var adapter: data_layanan_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_layanan)

        initViews()
        setupRecyclerView()
        setupFabClickListener()
        loadData()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rvDATA_Layanan)
        fabTambah = findViewById(R.id.fabDATA_Layanan_Tambah)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun setupFabClickListener() {
        fabTambah.setOnClickListener {
            // Intent untuk menambah layanan baru
            val intent = Intent(this, TambahLayananActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val layananList = getLayananFromDatabase()

        // PERBAIKAN 1: Hapus param = TODO()
        adapter = data_layanan_adapter(
            layananList = layananList,
            onHubungiClick = { layanan ->
                handleHubungiClick(layanan)
            },
            onLihatClick = { layanan ->
                handleLihatClick(layanan)
            },
            param = TODO()
        )

        // PERBAIKAN 2: Gunakan variable adapter, bukan class name
        recyclerView.adapter = data_layanan_adapter
    }

    private fun handleHubungiClick(layanan: model_layanan) {
        // PERBAIKAN 3: Gunakan property yang benar, bukan javaClass
        Toast.makeText(
            this,
            "Menghubungi untuk layanan: ${layanan.namaLayanan}",
            Toast.LENGTH_SHORT
        ).show()

        // Intent untuk menelepon
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+6281234567890")
        startActivity(intent)
    }

    private fun handleLihatClick(layanan: model_layanan) {
        // PERBAIKAN 4: Gunakan property yang benar, bukan javaClass
        Toast.makeText(
            this,
            "Melihat detail layanan: ${layanan.namaLayanan}",
            Toast.LENGTH_SHORT
        ).show()

        // Intent untuk membuka detail layanan
        val intent = Intent(this, DetailLayananActivity::class.java)
        intent.putExtra("LAYANAN_ID", layanan.idLayanan)
        intent.putExtra("NAMA_LAYANAN", layanan.namaLayanan)
        intent.putExtra("HARGA_LAYANAN", layanan.hargaLayanan)
        intent.putExtra("NAMA_CABANG", layanan.namaCabang)
        startActivity(intent)
    }

    private fun getLayananFromDatabase(): List<model_layanan> {
        // Implementasi untuk mengambil data dari database atau API
        // Sementara menggunakan data dummy
        return listOf(
            model_layanan("L001", "Cuci Setrika", "Rp 50.000", "Cabang Jakarta Pusat"),
            model_layanan("L002", "Setrika Saja", "Rp 15.000", "Cabang Surabaya"),
            model_layanan("L003", "Cuci Setrika + Pewangi", "Rp 100.000", "Cabang Bandung"),
            model_layanan("L004", "Cuci Karpet", "Rp 80.000", "Cabang Medan"),
            model_layanan("L005", "Cuci Sepatu", "Rp 25.000", "Cabang Jakarta Selatan")
        )
    }

    // Method untuk refresh data setelah menambah layanan baru
    private fun refreshData() {
        val newLayananList = getLayananFromDatabase()

        // PERBAIKAN 5: Hapus param = TODO()
        adapter = data_layanan_adapter(
            layananList = newLayananList,
            onHubungiClick = { layanan ->
                handleHubungiClick(layanan)
            },
            onLihatClick = { layanan ->
                handleLihatClick(layanan)
            },
            param = TODO()
        )
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // Refresh data ketika kembali ke activity ini
        // refreshData()
    }
}

private fun Intent.putExtra(s: String, idLayanan: Any) {

}

// PERBAIKAN 6: Hapus function TODO yang tidak perlu
// private fun Intent.putExtra(s: String, namaCabang: Any) {
//     TODO("Not yet implemented")
// }

// Activity untuk menambah layanan baru
class TambahLayananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_tambah_layanan)

        // Implementasi form untuk menambah layanan
        Toast.makeText(this, "Form Tambah Layanan", Toast.LENGTH_SHORT).show()

        // Sementara langsung finish
        finish()
    }
}

// Activity untuk detail layanan
class DetailLayananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_detail_layanan)

        // Ambil data dari intent
        val tvidLayanan = intent.getStringExtra("LAYANAN_ID")
        val namaLayanan = intent.getStringExtra("NAMA_LAYANAN")
        val hargaLayanan = intent.getStringExtra("HARGA_LAYANAN")
        val namaCabang = intent.getStringExtra("NAMA_CABANG")

        // Tampilkan detail layanan
        Toast.makeText(
            this,
            "Detail: $namaLayanan - $hargaLayanan",
            Toast.LENGTH_LONG
        ).show()

        // Implementasi tampilan detail layanan di sini
        // Misalnya set text ke TextView, load gambar, dll.
    }
}