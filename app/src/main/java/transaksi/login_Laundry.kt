// MainActivity.kt
package transaksi // Ganti dengan nama package aplikasi Anda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chelvie.laundry.R
import com.google.firebase.database.FirebaseDatabase



class Login_Laundry : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("login")
    lateinit var etPhone: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_laundry)
        init()
        getData()
        btnLogin.setOnClickListener{
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvLoginLaundry)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init(){
        etPhone = findViewById(R.id.tvNOHP)
        etPassword = findViewById(R.id.etPassword)
        btnLogin= findViewById(R.id.tvLoginLaundry)
    }
    fun getData(){
        val hp = intent.getStringExtra("noHp")
        val password = intent.getStringExtra("password")

        etPhone.setText(hp)
        etPassword.setText(password)

    }

    fun cekValidasi() {
        val phoneNumber = etPhone.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (phoneNumber.isEmpty()) {
            etPhone.error = "Nomor HP tidak boleh kosong"
            etPhone.requestFocus()
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Password tidak boleh kosong"
            etPassword.requestFocus()
            return
        }


        if (phoneNumber == "08971720403" && password == "12345") {
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Nomor HP atau Password salah.", Toast.LENGTH_SHORT).show()
        }
    }
}