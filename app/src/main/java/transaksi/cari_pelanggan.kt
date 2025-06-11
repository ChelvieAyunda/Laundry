package transaksi

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import com.chelvie.modeldata.model_pelanggan
import com.google.firebase.database.FirebaseDatabase

class cari_pelanggan : AppCompatActivity() {

    // Firebase setup
    private val database = FirebaseDatabase.getInstance()
    private val myReference = database.getReference("pelanggan")

    // UI Components
    private lateinit var cariPelanggan: RecyclerView
    private lateinit var pelangganList: ArrayList<model_pelanggan>
    private lateinit var tvKosong: TextView
    private lateinit var searchView: SearchView

    // SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences
    private var idCabang: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cari_pelanggan)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)

        // Initialize components
        init()

        // Get idCabang from SharedPreferences
        idCabang = sharedPreferences.getString("idCabang", "") ?: ""

        // Setup RecyclerView
        setupRecyclerView()

        // Load data
        loadPelangganData()
    }

    private fun init() {
        // Initialize UI components
        cariPelanggan = findViewById(R.id.tvCARIPELANGGAN)

        pelangganList = ArrayList()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        cariPelanggan.layoutManager = layoutManager

    }

    private fun loadPelangganData() {

    }
}