package transaksi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chelvie.laundry.R

class KonfirmasiData : AppCompatActivity() {


    private lateinit var tvName: TextView
    private lateinit var tvNOHP: TextView
    private lateinit var tvKeterangan: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvTambahan: TextView
    private lateinit var tvTOTAL: TextView
    private lateinit var tvHARGA: TextView
    private lateinit var btBatal: Button
    private lateinit var btPembayaran: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_data)


        tvName = findViewById(R.id.tvName)
        tvNOHP = findViewById(R.id.tvNOHP)
        tvKeterangan = findViewById(R.id.tvKeterangan)
        tvHarga = findViewById(R.id.tvharga)
        tvTambahan = findViewById(R.id.tvTambahan)
        tvTOTAL = findViewById(R.id.tvTOTAL)
        tvHARGA = findViewById(R.id.tvHARGA)
        btBatal = findViewById(R.id.btBatal)
        btPembayaran = findViewById(R.id.btPembayaran)


        tvName.text = "Najwa"
        tvNOHP.text = "08876256431"
        tvKeterangan.text = "Cuci Setrika 3kg"
        tvHarga.text = "Rp30.000"
        tvTambahan.text = "Layanan Tambahan"
        tvTOTAL.text = "Total Bayar"
        tvHARGA.text = "Rp50.000,00"


        btBatal.setOnClickListener {


            finish()
        }

        btPembayaran.setOnClickListener {

        }
    }
}