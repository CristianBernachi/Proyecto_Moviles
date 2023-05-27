package com.example.lista_series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BuscarActivity : AppCompatActivity() {

    lateinit var Edit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview2)
        val adapter = MyAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.visibility = View.VISIBLE //VUELVE INVISIBLE O VISIBLE EL RECYCLERVIEW, ya no se necesita xd


        Edit = findViewById(R.id.busqueda)

        Edit.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString()
                adapter.filter(searchText)
            }


        })
                    /*
                val instancia = MyAdapter()  // Acceder a una varible de una clase desde otra
                val valor = instancia.images //Ya no es necesario jaja
                */
    }
}
