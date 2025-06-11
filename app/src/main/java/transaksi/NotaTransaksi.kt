package transaksi

import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R

class NotaTransaksi(NotaTransaksi1: NotaTransaksi, NotaTransaksi: Nothing, onItemClick: Nothing) : AppCompatActivity() {

    // Views
    private lateinit var tvIdTransaksi: TextView
    private lateinit var tvTanggal: TextView
    private lateinit var tvPesanan: TextView
    private lateinit var tvKaryawan: TextView
    private lateinit var tvCuciSetrika: TextView
    private lateinit var tvHargaCuciSetrika: TextView
    private lateinit var tvHargaSubtotalTambahan: TextView
    private lateinit var buttonKirimWhatsapp: Button
    private lateinit var buttonCetak: Button
    private lateinit var buttonTransaksiSukses: Button
    private lateinit var recyclerViewAdditionalItems: RecyclerView

    // Adapter
    private lateinit var NotaTransaksi: NotaTransaksi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_transaksi)

        initializeViews()
        setupUI()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun initializeViews() {
        tvIdTransaksi = findViewById(R.id.tvIdTransaksi)
        tvTanggal = findViewById(R.id.tvTanggal)
        tvPesanan = findViewById(R.id.tvPesanan)
        tvKaryawan = findViewById(R.id.tvKaryawan)
        tvCuciSetrika = findViewById(R.id.tvCuciSetrika)
        tvHargaCuciSetrika = findViewById(R.id.tvHargaCuciSetrika)
        tvHargaSubtotalTambahan = findViewById(R.id.tvHargaSubtotalTambahan)
        recyclerViewAdditionalItems = findViewById(R.id.recyclerViewAdditionalItems)
        buttonKirimWhatsapp = findViewById(R.id.buttonKirimWhatsapp)
        buttonCetak = findViewById(R.id.buttonCetak)
        buttonTransaksiSukses = findViewById(R.id.buttonTransaksiSukses)
    }

    private fun setupUI() {
        // Set transaction data (in a real app, this would come from intent extras)
        tvIdTransaksi.text = "-QQBFF4tpoCrkBBN9Uzv"
        tvTanggal.text = "2025-05-14 07:47:17"
        tvPesanan.text = "Adel"
        tvKaryawan.text = "Billa"
        tvCuciSetrika.text = "Cuci Setrika 5kg"
        tvHargaCuciSetrika.text = "Rp40.000,00"
        tvHargaSubtotalTambahan.text = "Rp30.000,00" // 3x Rp10,000
    }

    private fun setupRecyclerView() {
        // Create mock data for additional services
        val additionalServices = listOf(
            AdditionalService("Pewangi Aroma Melati", "Rp10.000,00"),
            AdditionalService("Pemutih", "Rp15.000,00"),
            AdditionalService("Pewangi Sakura", "Rp10.000,00")
        )

        NotaTransaksi = NotaTransaksi(
            NotaTransaksi,
            NotaTransaksi = TODO(),
            onItemClick = TODO()
        )
        recyclerViewAdditionalItems.apply {
            layoutManager = LinearLayoutManager(this@NotaTransaksi)
            adapter = adapter
            setHasFixedSize(true)
        }
    }

    private fun setupClickListeners() {
        buttonKirimWhatsapp.setOnClickListener {
            sendWhatsAppMessage()
        }

        buttonCetak.setOnClickListener {
            Toast.makeText(
                this,
                "Mencetak struk...",
                Toast.LENGTH_SHORT
            ).show()
        }

        buttonTransaksiSukses.setOnClickListener {
            finish()
        }
    }

    private fun sendWhatsAppMessage() {
        val phoneNumber = "081234567890"
        val message = """
            *CleanWave Laundry - Nota Transaksi*
            
            ID Transaksi: ${tvIdTransaksi.text}
            Tanggal: ${tvTanggal.text}
            Pelanggan: ${tvPesanan.text}
            
            *Layanan Utama:*
            ${tvCuciSetrika.text} - ${tvHargaCuciSetrika.text}
            
            *Layanan Tambahan:*
            - Pewangi Aroma Melati - Rp10.000,00 (x3)
            
            *Total: Rp70.000,00*
            
            Terima kasih telah menggunakan CleanWave Laundry!
        """.trimIndent()

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://wa.me/$phoneNumber/?text=${Uri.encode(message)}")
        startActivity(intent)
    }
}

// Data class for additional services
data class AdditionalService(
    val name: String,
    val price: String
)

// Adapter class for RecyclerView
class AdditionalServiceAdapter(
    private val serviceList: List<AdditionalService>
) : RecyclerView.Adapter<AdditionalServiceAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceName: TextView = itemView.findViewById(R.id.tvAdditionalItemName)
        val servicePrice: TextView = itemView.findViewById(R.id.tvAdditionalItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_tambahan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = serviceList[position]
        holder.serviceName.text = currentItem.name
        holder.servicePrice.text = currentItem.price
    }

    override fun getItemCount(): Int = serviceList.size
}
