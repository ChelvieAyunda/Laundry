package transaksi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chelvie.laundry.R

class Pembayaran : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pembayaran)
        setFinishOnTouchOutside(false)

        val btnBayarNanti: Button = findViewById(R.id.btn_bayar_nanti)
        val btnTunai: Button = findViewById(R.id.btn_tunai)
        val btnQris: Button = findViewById(R.id.btn_qris)
        val btnDana: Button = findViewById(R.id.btn_dana)
        val btnGopay: Button = findViewById(R.id.btn_gopay)
        val btnOvo: Button = findViewById(R.id.btn_ovo)
        val tvBatal: TextView = findViewById(R.id.tvBatal)


        val listener = { metode: String ->
            val resultIntent = Intent(this, Pembayaran::class.java)
            resultIntent.putExtra("metodePembayaran", metode)
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Tutup dialog/activity ini
        }

        btnBayarNanti.setOnClickListener { listener("Bayar Nanti") }
        btnTunai.setOnClickListener { listener("Tunai") }
        btnQris.setOnClickListener { listener("QRIS") }
        btnDana.setOnClickListener { listener("DANA") }
        btnGopay.setOnClickListener { listener("GoPay") }
        btnOvo.setOnClickListener { listener("OVO") }

        tvBatal.setOnClickListener {
            setResult(Activity.RESULT_CANCELED) // Kirim hasil bahwa dibatalkan
            finish() // Tutup dialog/activity ini
        }
    }

    // Opsional: Agar tombol back sistem juga dianggap sebagai batal
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }
}