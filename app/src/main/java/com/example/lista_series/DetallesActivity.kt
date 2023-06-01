package com.example.lista_series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class DetallesActivity : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var image: ImageView
    lateinit var detalle: TextView

    val Status = listOf<String>(
            "Sin Empezar",
            "Viendo",
            "Terminado",
            "Dropped"
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        supportActionBar?.hide()

        val titulo = intent.getStringExtra("titulo")
        val detalles = intent.getStringExtra("detalles")
        val imagen = intent.getIntExtra("imagen", 0)
        var status = intent.getIntExtra("status", 0)
        val series = SeriesProvider.SeriesList.find { it.titulo == titulo }

        val spinner = findViewById<Spinner>(R.id.spinner_detalles)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Status)

        spinner.adapter = adapter
        spinner.setSelection(status)

        title = findViewById(R.id.detalles_titulo)
        image = findViewById(R.id.detalles_imagen)
        detalle = findViewById(R.id.detalles_detalle)

        title.text = titulo
        image.setImageResource(imagen)
        detalle.text= detalles

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = spinner.selectedItemPosition
                if (series != null) {
                    series.status = selectedItem
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


    }
}