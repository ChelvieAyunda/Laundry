package transaksi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.chelvie.laundry.R
import transaksi.model_layanan_tambahan
import java.text.NumberFormat
import java.util.Locale

class LayananTambahanAdapter(
    private val context: LayananTambahanAdapter,
    private val listLayananTambahan: ArrayList<model_layanan_tambahan>,
    private val onItemClick: (model_layanan_tambahan) -> Unit
) : RecyclerView.Adapter<LayananTambahanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pilih_layanan, parent, false) //
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listLayananTambahan[position]

        holder.tvIdLayananTambahan.text = item.idLayananTambahan ?: "N/A"
        holder.tvNamaLayananTambahan.text = item.namaLayananTambahan ?: "Nama Tidak Tersedia"

        val formatRupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val hargaFormatted = item.hargaLayananTambahan?.let { formatRupiah.format(it) } ?: "Rp 0"
        holder.tvHargaLayananTambahan.text = "Harga : $hargaFormatted"

        holder.cvCard.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = listLayananTambahan.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Sesuaikan ID dengan yang ada di card_pilih_layanan.xml
        // Root CardView tidak memiliki ID di card_pilih_layanan.xml, jadi kita gunakan itemView untuk klik jika perlu
        // Namun, disarankan menambahkan ID pada CardView terluar di XML
        val cvCard: CardView = itemView as CardView // Asumsi itemView adalah CardView itu sendiri
        val tvIdLayananTambahan: TextView = itemView.findViewById(R.id.tvPelanggan) // ID untuk header ID di card_pilih_layanan.xml
        val tvNamaLayananTambahan: TextView = itemView.findViewById(R.id.tvNama) // ID untuk nama
        val tvHargaLayananTambahan: TextView = itemView.findViewById(R.id.tvHargaLayanan) // ID untuk harga
    }
}