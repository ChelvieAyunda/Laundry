package transaksi

import android.os.Parcel
import android.os.Parcelable

data class model_layanan_tambahan(
    val idLayananTambahan: String? = null,
    val namaLayananTambahan: String? = null,
    val hargaLayananTambahan: String? = null,
    val idCabang: String? = null
) : Parcelable {
    val id: Any
        get() {
            TODO()
        }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}