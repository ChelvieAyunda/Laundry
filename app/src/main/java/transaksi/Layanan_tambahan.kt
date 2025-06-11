// DataLayananTambahan Activity
package transaksi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.ModelData.ModelLayananTambahan
import com.chelvie.laundry.R
import com.google.firebase.database.*
import transaksi.adapter.LayananTambahanAdapter

@Suppress("UNREACHABLE_CODE")
class Layanan_tambahan : AppCompatActivity() {
    private lateinit var rvLayananTambahan: RecyclerView
    private lateinit var layananTambahanList: ArrayList<model_layanan_tambahan>
    private lateinit var adapter: LayananTambahanAdapter
    private lateinit var dbRef: DatabaseReference
    private var selectionMode = false
    private var idCabangFilter: String? = null

    companion object {
        private const val TAG = "Layanan Tambahan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_tambahan)

        // Get intent data
        selectionMode = intent.getBooleanExtra("selectionMode", false)
        idCabangFilter = intent.getStringExtra("idCabang")

        initViews()
        setupRecyclerView()
        setupFirebase()
        fetchData()
    }

    private fun initViews() {
        rvLayananTambahan = findViewById(R.id.rvDATA_Layanan)
    }

    private fun setupRecyclerView() {
        rvLayananTambahan.layoutManager = LinearLayoutManager(this)
        layananTambahanList = arrayListOf()

        adapter = LayananTambahanAdapter(
            layananTambahanList,
            listLayananTambahan = TODO()
        ) { layananTambahan ->
            if (selectionMode) {
                // Return selected item
                val resultIntent = Intent().apply {
                    putExtra("selectedLayananTambahan", layananTambahan)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Show details or edit - replace shortArrayOf with proper action
                showLayananTambahanDetails(layananTambahan)
            }
        }

        rvLayananTambahan.adapter = adapter
    }

    private fun putExtra(s: String, layananTambahan: ModelLayananTambahan) {
        TODO("Not yet implemented")
    }

    private fun setupFirebase() {
        dbRef = FirebaseDatabase.getInstance().getReference("layanan_tambahan")
    }

    private fun fetchData() {
        val query = if (idCabangFilter != null) {
            dbRef.orderByChild("idCabang").equalTo(idCabangFilter)
        } else {
            dbRef
        }

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                layananTambahanList.clear()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        try {
                            val layananTambahan = dataSnapshot.getValue(model_layanan_tambahan::class.java)
                            layananTambahan?.let {
                                layananTambahanList.add(it)
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Error parsing data: ${e.message}")
                        }
                    }
                }
                adapter.notifyDataSetChanged()

                if (layananTambahanList.isEmpty()) {
                    Log.d(TAG, "No data found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Database error: ${error.message}")
                Toast.makeText(this@Layanan_tambahan, "Error loading data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLayananTambahanDetails(layananTambahan: ModelLayananTambahan) {
        // Implement your logic here for showing details or editing
        // For example:
        Toast.makeText(this, "Selected: ${layananTambahan.namaLayananTambahan}", Toast.LENGTH_SHORT).show()

    }
}