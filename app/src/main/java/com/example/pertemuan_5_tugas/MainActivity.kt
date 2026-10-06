package com.example.pertemuan_5_tugas

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var role = "Admin"
    private lateinit var tvRole: TextView

    private val roleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            role = result.data?.getStringExtra("role") ?: role
            tvRole.text = role
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvRole = findViewById(R.id.tvRole)
        tvRole.text = role

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.isItemActiveIndicatorEnabled = false

        val sectionemail = findViewById<LinearLayout>(R.id.sectionemail)
        sectionemail.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("sarah@school.edu"))
                putExtra(Intent.EXTRA_SUBJECT, "Subject")
            }
            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi email", Toast.LENGTH_SHORT).show()
            }
        }

        val sectiontelepon = findViewById<LinearLayout>(R.id.sectiontelepon)
        sectiontelepon.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+15559876547")
            }
            try {
                startActivity(dialIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi telepon", Toast.LENGTH_SHORT).show()
            }
        }

        val sectionrole = findViewById<LinearLayout>(R.id.sectionrole)
        sectionrole.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            roleLauncher.launch(intent)
        }
    }
}
