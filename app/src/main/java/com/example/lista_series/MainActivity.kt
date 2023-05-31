package com.example.lista_series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRV()

        SeriesProvider.SeriesList.add(
            Series(
                "PRUEBA",
                "PRUEBA",
                R.drawable.mt
            )
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu)//Infla el menu de opciones
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {//Determina con que parte del menu se interactúo
            R.id.buscar -> cambiarActivity(1)
            R.id.conf -> cambiarActivity(2)
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun cambiarActivity(A: Int): Boolean{
        when(A){//Determina a que activity cambiar dependiendo del menu

            1 -> {
                val act = Intent(this,BuscarActivity::class.java)
                startActivity(act)
            }
            2 -> {
                val act = Intent(this,OpcionesActivity::class.java)
                startActivity(act)
            }
        }

        return true
    }

    fun initRV(){

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview) //Implementa el RecyclerView y su Adapter
        val adapter = MyAdapter(SeriesProvider.SeriesList)

        adapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                val clickedItem = SeriesProvider.SeriesList[position]
                val intent = Intent(this@MainActivity, DetallesActivity::class.java).apply {
                    putExtra("detalles", clickedItem.detalles)
                    putExtra("imagen", clickedItem.imagen)
                    putExtra("titulo", clickedItem.titulo)
                }
                this@MainActivity.startActivity(intent)
                //Toast.makeText( this@MainActivity,position.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)//Modifica el LayoutManager del Recycler
        recyclerView.adapter = adapter
    }
}

/*val instancia = MyAdapter()  // Acceder a una varible de una clase desde otra
val titulos = instancia.titulos
val detalles = instancia.detalles
val imagen = instancia.images*/