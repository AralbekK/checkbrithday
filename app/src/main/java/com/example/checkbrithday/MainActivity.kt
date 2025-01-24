package com.example.checkbrithday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var photoImageView: ImageView
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstNameEditText: EditText = findViewById(R.id.firstNameEditText)
        val lastNameEditText: EditText = findViewById(R.id.lastNameEditText)
        val birthDateEditText: EditText = findViewById(R.id.birthDateEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        photoImageView = findViewById(R.id.photoImageView)
        val addPhotoButton: Button = findViewById(R.id.addPhotoButton)
        val saveButton: Button = findViewById(R.id.saveButton)

        // Обработчик выбора фото из галереи
        val selectPhotoLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                photoUri = it
                photoImageView.setImageURI(it)
            }
        }

        addPhotoButton.setOnClickListener {
            selectPhotoLauncher.launch("image/*")
        }

        saveButton.setOnClickListener {
            val phoneText = phoneEditText.text.toString()

            // Проверка, чтобы строка "номер телефона" содержала только цифры
            if (phoneText.matches("\\d+".toRegex())) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("FIRST_NAME", firstNameEditText.text.toString())
                    putExtra("LAST_NAME", lastNameEditText.text.toString())
                    putExtra("BIRTH_DATE", birthDateEditText.text.toString())
                    putExtra("PHONE", phoneText)
                    putExtra("PHOTO", photoUri)
                }
                startActivity(intent)
            } else {
                // Показываем сообщение об ошибке
                Toast.makeText(this, "Пожалуйста, введите корректный номер телефона", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
