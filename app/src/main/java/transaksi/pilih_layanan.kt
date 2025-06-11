package transaksi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.adapter.pilih_layanan_adapter
import com.chelvie.laundry.ModelData.modelPilihLayanan
import com.chelvie.laundry.R
import com.chelvie.modeldata.model_layanan
import java.util.ArrayList

class pilih_layanan : AppCompatActivity() {

    private lateinit var recyclerViewLayanan: RecyclerView
    private lateinit var layananAdapter: pilih_layanan_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_layanan)

        recyclerViewLayanan = findViewById(R.id.tvPILIHLAYANAN)

        setupRecyclerView()
        loadLayananData() // Call this to load data
    }

    private fun setupRecyclerView() {
        recyclerViewLayanan.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with empty list and click listener
        layananAdapter = pilih_layanan_adapter(ArrayList()) { selectedLayanan ->
            // Handle item click here
            handleLayananSelection(selectedLayanan)
        }

        // Set adapter to RecyclerView
        recyclerViewLayanan.adapter = layananAdapter
    }

    private fun handleLayananSelection(layanan: model_layanan) {
        // Handle what happens when a layanan is selected
        // For example, you might want to pass data to next activity
        // or show a dialog, etc.
    }

    private fun loadLayananData() {
        // In a real application, you would fetch this data from a database (e.g., Firebase)
        // For demonstration, let's create some mock data:
        val mockLayananList = listOf(
            modelPilihLayanan("id1", "Cuci Kering", 7000.0, "kg", "cabangA", "1 hari"),
            modelPilihLayanan("id2", "Cuci Setrika", 10000.0, "kg", "cabangA", "2 hari"),
            modelPilihLayanan("id3", "Setrika Saja", 5000.0, "pcs", "cabangB", "1 hari"),
            modelPilihLayanan("id4", "Dry Clean Jas", 25000.0, "pcs", "cabangB", "3 hari")
        )

        // Update adapter with loaded data
        layananAdapter.updateData(mockLayananList)
    }
}