package com.example.checkbrithday

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DataCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_card)  // Убедись, что ID макета совпадает

        val dataTextView: TextView = findViewById(R.id.dataTextView)
        dataTextView.text = "Your Data Here"
    }
}
