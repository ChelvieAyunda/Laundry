package transaksi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R // Assuming this is your R file for resources
import com.chelvie.modeldata.model_layanan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class data_layanan_adapter(
    layananList: List<model_layanan>,
    param: (Any) -> Unit,
    onHubungiClick: Any,
    onLihatClick: Any
) : AppCompatActivity() {

    private lateinit var rvDataLayanan: RecyclerView
    private lateinit var fabTambahLayanan: FloatingActionButton
    private lateinit var layananList: ArrayList<model_layanan>
    private lateinit var layananAdapter: data_layanan_adapter // We'll create this adapter

    private lateinit var dbRefLayanan: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_layanan) // Ensure this matches your XML file name

        rvDataLayanan = findViewById(R.id.rvDATA_Layanan) // ID from your XML
        fabTambahLayanan = findViewById(R.id.fabDATA_Layanan_Tambah) // ID from your XML

        // Initialize Firebase Database reference
        dbRefLayanan = FirebaseDatabase.getInstance("https://laundryu-9e220-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("layanan")

        setupRecyclerView()
        setupFabClickListener()
        fetchLayananData() // Function to fetch data from Firebase
    }

    private fun setupRecyclerView() {
        rvDataLayanan.layoutManager = LinearLayoutManager(this)
        layananList = ArrayList() // Initialize empty list
        layananAdapter = data_layanan_adapter (layananList, { layanan ->
            // Handle item click (e.g., for editing a service)
            val intent = Intent(this, data_layanan_adapter::class.java)
            intent.putExtra("isEdit", true)
            intent.putExtra("idLayanan", layanan.idLayanan)
            intent.putExtra("namaLayanan", layanan.namaLayanan)
            intent.putExtra("hargaLayanan", layanan.hargaLayanan)
            intent.putExtra("satuan", layanan.satuan)
            intent.putExtra("estimasiWaktu", layanan.estimasiWaktu)
            intent.putExtra("idCabang", layanan.idCabang) // Pass idCabang for spinner pre-selection
            startActivity(intent)
        }, { layanan ->
            // Intent untuk menelepon atau membuka WhatsApp
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+6281234567890")
            startActivity(intent)
        }) { layanan ->
            // Intent untuk membuka detail layanan
            val intent = Intent(this, DetailLayananActivity::class.java)
            intent.putExtra("LAYANAN_ID", layanan.idLayanan)
            startActivity(intent)
        }
        rvDataLayanan.adapter = layananAdapter
    }

    private fun setupFabClickListener() {
        fabTambahLayanan.setOnClickListener {
            // Start TambahLayananActivity to add a new service
            val intent = Intent(this, data_layanan_adapter::class.java)
            intent.putExtra("isEdit", false) // Indicate that it's for adding new
            startActivity(intent)
        }
    }

    private fun fetchLayananData() {
        dbRefLayanan.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                layananList.clear() // Clear existing data to avoid duplicates
                if (snapshot.exists()) {
                    for (layananSnap in snapshot.children) {
                        val layanan = layananSnap.getValue(model_layanan::class.java)
                        layanan?.let {
                            layananList.add(it)
                        }
                    }
                }
                layananAdapter.notifyDataSetChanged() // Notify adapter that data has changed
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@data_layanan_adapter, "Failed to load services: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun notifyDataSetChanged() {
            TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        // Re-fetch data every time the activity comes to the foreground
        // This ensures the list is updated after adding/editing a service
        fetchLayananData()
    }
}