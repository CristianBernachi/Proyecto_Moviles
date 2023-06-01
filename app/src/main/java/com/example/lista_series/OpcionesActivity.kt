package com.example.lista_series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate

class OpcionesActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var toggleButton: ToggleButton
    private val options = arrayOf("Activar/Desactivar notificaciones", "Cambiar tema de la aplicación", "Notificar un error")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        listView = findViewById(R.id.ListaOpciones)
        toggleButton = findViewById(R.id.toggleButton)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = options[position]
            showAlertDialog(selectedItem)
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Enable dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Disable dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }

    private fun showAlertDialog(option: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿$option?")

        if (option == options.last()) {
            // Create an EditText for user input
            val inputEditText = EditText(this)
            inputEditText.hint = "Tu error"
            builder.setView(inputEditText)

            builder.setPositiveButton("Aceptar") { dialog, _ ->
                val userInput = inputEditText.text.toString()
                val message = "Tu error se notificará a un administrador."
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        } else {
            builder.setPositiveButton("Aceptar") { dialog, _ ->
                val message = "Se cambiarán las preferencias."
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            // Handle Cancel button click
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

}