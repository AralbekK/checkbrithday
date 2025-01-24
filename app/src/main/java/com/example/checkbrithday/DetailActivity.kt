package com.example.checkbrithday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class DetailActivity : AppCompatActivity() {
    private lateinit var photoImageView: ImageView
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Инициализация переменных
        photoImageView = findViewById(R.id.photoImageView)
        val changePhotoButton: Button = findViewById(R.id.changePhotoButton)

        // Получение данных, переданных с первого экрана
        val firstName = intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")
        val birthDate = intent.getStringExtra("BIRTH_DATE")
        val phone = intent.getStringExtra("PHONE")
        photoUri = intent.getParcelableExtra("PHOTO")

        // Отображение данных на экране
        photoImageView.setImageURI(photoUri)
        findViewById<TextView>(R.id.nameTextView).text = "$firstName $lastName"
        findViewById<TextView>(R.id.birthDateTextView).text = birthDate
        findViewById<TextView>(R.id.phoneTextView).text = phone

        // Обработчик выбора нового фото из галереи
        val selectPhotoLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                photoUri = it
                photoImageView.setImageURI(it)
            }
        }

        // Обработчик нажатия кнопки изменения фото
        changePhotoButton.setOnClickListener {
            selectPhotoLauncher.launch("image/*")
        }
    }

    // Настройка меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    // Обработчик выбора пунктов меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                // Вывод тоста и завершение приложения
                Toast.makeText(this, "Программа завершена", Toast.LENGTH_SHORT).show()
                finishAffinity()
                exitProcess(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
