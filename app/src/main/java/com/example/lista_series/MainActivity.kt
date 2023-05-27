package com.example.lista_series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview) //Implementa el RecyclerView y su Adapter
        val adapter = MyAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this) //Modifica el LayoutManager del Recycler
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu) //Infla el menu de opciones
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) { //Determina con que parte del menu se interactúo
            R.id.buscar -> cambiarActivity(1)
            R.id.conf -> cambiarActivity(2)
            else -> super.onOptionsItemSelected(item)
        }


    }

    fun cambiarActivity(A: Int): Boolean{
        when(A){ //Determina a que activity cambiar dependiendo del menu

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
}