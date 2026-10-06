package com.example.pertemuan_5_tugas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<TextView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val adminButton = findViewById<Button>(R.id.adminbutton)
        val userButton = findViewById<Button>(R.id.userbutton)
        val guestButton = findViewById<Button>(R.id.guestbutton)

        adminButton.setOnClickListener { kirimRole("Admin") }
        userButton.setOnClickListener { kirimRole("User") }
        guestButton.setOnClickListener { kirimRole("Guest") }
    }

    private fun kirimRole(role: String) {
        val hasil = Intent().apply {
            putExtra("role", role)
        }
        setResult(RESULT_OK, hasil)
        finish() // kembali ke halaman sebelumnya
    }
}
