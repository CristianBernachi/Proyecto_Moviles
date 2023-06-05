package com.example.lista_series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class DetallesActivity : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var image: ImageView
    lateinit var detalle: TextView
    lateinit var lista: ListView
    lateinit var spinner: Spinner
    lateinit var statusCapitulos: MutableList<Boolean>


    val Status = listOf<String>(
            "Sin Empezar",
            "Viendo",
            "Terminado",
            "Dropped"
        )


    val capitulosList = mutableListOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        supportActionBar?.hide()


        title = findViewById(R.id.detalles_titulo)
        image = findViewById(R.id.detalles_imagen)
        detalle = findViewById(R.id.detalles_detalle)
        spinner = findViewById<Spinner>(R.id.spinner_detalles)
        lista = findViewById(R.id.detalles_lista)

        val titulo = intent.getStringExtra("titulo")
        val detalles = intent.getStringExtra("detalles")
        val imagen = intent.getIntExtra("imagen", 0)
        var status = intent.getIntExtra("status", 0)
        var capitulos = intent.getIntExtra("capitulos", 0)
        val series = SeriesProvider.SeriesList.find { it.titulo == titulo }

        val adapterL = ArrayAdapter(this, android.R.layout.simple_list_item_1, capitulosList)
        val adapterS = ArrayAdapter(this, android.R.layout.simple_spinner_item, Status)

        statusCapitulos = MutableList(capitulos){ false }

        spinner.adapter = adapterS
        lista.adapter = adapterL

        spinner.setSelection(status)


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

        for(i in capitulos downTo  1){
            capitulosList.add(i)
        }

        lista.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            if (statusCapitulos[position]) {
                statusCapitulos[position] = false

                val selectedView = lista.getChildAt(position - lista.firstVisiblePosition)
                if (selectedView != null) {
                    val textView = selectedView.findViewById<TextView>(android.R.id.text1)
                    textView.text = capitulosList[position].toString()
                }
            } else {
                statusCapitulos[position] = true

                val selectedView = lista.getChildAt(position - lista.firstVisiblePosition)
                if (selectedView != null) {
                    val textView = selectedView.findViewById<TextView>(android.R.id.text1)
                    textView.text = "${capitulosList[position]} - Visto"
                }
            }
        }


    }
}