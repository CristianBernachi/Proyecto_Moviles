package com.example.lista_series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var filterlist: MutableList<Series> = mutableListOf()
    lateinit var radioGroup: RadioGroup
    lateinit var rbViendo: RadioButton
    lateinit var rbTerminado: RadioButton
    lateinit var rbDropped: RadioButton
    lateinit var rbTodas: RadioButton
    var Series = SeriesProvider.SeriesList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabRefresh = findViewById<Button>(R.id.fab_refresh)
        fabRefresh.setOnClickListener {
            updateMainActivity()
        }

        initRV()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.buscar -> cambiarActivity(1)
            R.id.conf -> cambiarActivity(2)
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun cambiarActivity(A: Int): Boolean{

        when(A){

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

        filterlist = Series.filter { it.fav==true }.toMutableList()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MyAdapter(filterlist)

        recyclerView.layoutManager = LinearLayoutManager(this)//Modifica el LayoutManager del Recycler
        recyclerView.adapter = adapter

        radioGroup = findViewById(R.id.rg_main)
        rbViendo = findViewById(R.id.rb_viendo)
        rbTerminado = findViewById(R.id.rb_terminado)
        rbDropped = findViewById(R.id.rb_dropped)
        rbTodas = findViewById(R.id.rb_todas)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_todas -> filterlist = adapter.filterStatus(0)
                R.id.rb_viendo -> filterlist = adapter.filterStatus(1)
                R.id.rb_terminado -> filterlist = adapter.filterStatus(2)
                R.id.rb_dropped -> filterlist = adapter.filterStatus(3)
            }
        }
        adapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {

                val clickedItem = filterlist[position]
                val intent = Intent(this@MainActivity, DetallesActivity::class.java).apply {
                    putExtra("imagen", clickedItem.imagen)
                    putExtra("titulo", clickedItem.titulo)
                    putExtra("status", clickedItem.status)
                    putExtra("capitulos", clickedItem.capitulos)
                    putExtra("detalles", clickedItem.detallescompletos)
                }

                this@MainActivity.startActivity(intent)

            }
        })
    }
    private fun updateMainActivity() {
        initRV()
    }

}