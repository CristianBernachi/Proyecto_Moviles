package com.example.lista_series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BuscarActivity : AppCompatActivity() {

    lateinit var Edit : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview2)
        val adapter = MyAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.visibility = View.INVISIBLE //VUELVE INVISIBLE EL RECYCLERVIEW


        Edit= findViewById(R.id.busqueda)

        Edit.setOnClickListener{ view ->
            TODO() //METODO DEL EDITTEXT PARA LA BUSQUEDA

        }

    }
    /*
    val instancia = MyAdapter()  // Acceder a una varible de una clase desde otra
    val valor = instancia.images
    */
}
