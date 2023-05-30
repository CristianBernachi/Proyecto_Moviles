package com.example.lista_series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetallesActivity : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val detalles = intent.getStringExtra("detalles")
        val imagen = intent.getIntExtra("imagen", 0)
        val titulo = intent.getStringExtra("titulo")


        title = findViewById(R.id.detalles_titulo)
        image = findViewById(R.id.detalles_imagen)

        title.text = titulo
        image.setImageResource(imagen)
    }
}