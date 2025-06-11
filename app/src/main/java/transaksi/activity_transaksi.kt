package transaksi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.adapter.transaksi_tambahan_adapter
import com.chelvie.laundry.R

class activity_transaksi : AppCompatActivity() {

    private lateinit var btPilihPelanggan: Button
    private lateinit var btPilihLayanan: Button
    private lateinit var btnProses: Button
    private lateinit var btTambahan: Button

    private lateinit var tvNamaPelanggan: TextView
    private lateinit var tvNoHpPelanggan: TextView
    private lateinit var tvLayananNama: TextView
    private lateinit var tvLayananHarga: TextView
    private lateinit var tvKosong: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: transaksi_tambahan_adapter

    private var namaPelanggan: String? = null
    private var noHpPelanggan: String? = null
    private var namaLayanan: String? = null
    private var hargaLayanan: String? = null

    private val pilihPelanggan = 1
    private val pilihLayanan = 2
    private val pilihTambahan = 3

    // Flag untuk mengecek apakah fitur tambahan tersedia
    private val hasTambahanFeature = true // Set ke true jika fitur tambahan tersedia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)

        // Inisialisasi views
        tvNamaPelanggan = findViewById(R.id.tvNAMAPELANGGAN)
        tvNoHpPelanggan = findViewById(R.id.tvNOHP)
        tvLayananNama = findViewById(R.id.tvNAMALAYANAN)
        tvLayananHarga = findViewById(R.id.tvHargaLayanan)
        btPilihPelanggan = findViewById(R.id.btnPILIHPELANGGAN)
        btPilihLayanan = findViewById(R.id.btnPILIHLAYANAN)
        btnProses = findViewById(R.id.btProses)
        btTambahan = findViewById(R.id.btTambahan)

        // Setup RecyclerView dan komponen tambahan hanya jika fitur tersedia
        if (hasTambahanFeature) {
            recyclerView = findViewById(R.id.rvTRANSAKSI)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)

            // Inisialisasi adapter setelah tvKosong diinisialisasi
            adapter = transaksi_tambahan_adapter(this, mutableListOf(), tvKosong)
            recyclerView.adapter = adapter

            tvKosong.visibility = View.VISIBLE
        } else {
            // Sembunyikan komponen tambahan jika tidak tersedia
            btTambahan.visibility = View.GONE
            findViewById<RecyclerView>(R.id.rvTRANSAKSI).visibility = View.GONE
            // Sembunyikan tvKosong jika tidak ada fitur tambahan
            tvKosong.visibility = View.GONE
        }

        btPilihPelanggan.setOnClickListener {
            val intent = Intent(this, pilihPelanggan::class.java)
            startActivityForResult(intent, pilihPelanggan)
        }

        btPilihLayanan.setOnClickListener {
            val intent = Intent(this, pilihLayanan::class.java)
            startActivityForResult(intent, pilihLayanan)
        }

        btTambahan.setOnClickListener {
            if (hasTambahanFeature) {
                val intent = Intent(this, Layanan_tambahan::class.java)
                startActivityForResult(intent, pilihTambahan)
            } else {
                Toast.makeText(this, "Fitur tambahan belum tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        btnProses.setOnClickListener {
            if (namaPelanggan == null || namaLayanan == null) {
                Toast.makeText(this, "Lengkapi data pelanggan dan layanan!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, KonfirmasiData::class.java)
            intent.putExtra("namaPelanggan", namaPelanggan)
            intent.putExtra("noHP", noHpPelanggan)
            intent.putExtra("namaLayanan", namaLayanan)
            intent.putExtra("hargaLayanan", hargaLayanan)

            // Kirim data tambahan hanya jika fitur tersedia dan ada data
            if (hasTambahanFeature && ::adapter.isInitialized) {
                intent.putParcelableArrayListExtra("dataTambahan", ArrayList(adapter.getSelectedItems()))
            } else {
                // Kirim list kosong jika tidak ada tambahan
                intent.putParcelableArrayListExtra("dataTambahan", ArrayList<model_layanan_tambahan>())
            }

            startActivity(intent)
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                pilihPelanggan -> {
                    namaPelanggan = data.getStringExtra("nama")
                    noHpPelanggan = data.getStringExtra("noHP")
                    tvNamaPelanggan.text = "Nama: $namaPelanggan"
                    tvNoHpPelanggan.text = "No HP: $noHpPelanggan"
                }
                pilihLayanan -> {
                    namaLayanan = data.getStringExtra("nama")
                    hargaLayanan = data.getStringExtra("harga")
                    tvLayananNama.text = "Nama Layanan: $namaLayanan"
                    tvLayananHarga.text = "Harga: $hargaLayanan"
                }
                pilihTambahan -> {
                    if (hasTambahanFeature && ::adapter.isInitialized) {
                        val id = data.getStringExtra("idLayanan")
                        val nama = data.getStringExtra("nama")
                        val harga = data.getStringExtra("harga")

                        if (!id.isNullOrEmpty() && !nama.isNullOrEmpty() && !harga.isNullOrEmpty()) {
                            val tambahan = model_layanan_tambahan(id, nama, harga)
                            adapter.addSelectedItem(tambahan) // Perbaikan: ganti .tra() dengan .addSelectedItem()

                            // Update visibility tvKosong
                            if (adapter.itemCount > 0) {
                                tvKosong.visibility = View.GONE
                            } else {
                                tvKosong.visibility = View.VISIBLE
                            }

                            recyclerView.scrollToPosition(adapter.itemCount - 1)
                        } else {
                            Toast.makeText(this, "Data tambahan tidak valid", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}