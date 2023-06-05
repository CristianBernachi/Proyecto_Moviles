package com.example.lista_series

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import java.text.SimpleDateFormat
import java.util.*

class OpcionesActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    private lateinit var listView: ListView
    private lateinit var toggleButton: ToggleButton

    private lateinit var btnDateTimePicker: Button
    private lateinit var tvSelectedDateTime: TextView
    private lateinit var calendar: Calendar



    private val options = arrayOf("Activar/Desactivar notificaciones", "Cambiar tema de la aplicación", "Notificar un error")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)



        listView = findViewById(R.id.ListaOpciones)
        toggleButton = findViewById(R.id.toggleButton)

        btnDateTimePicker = findViewById(R.id.btnDateTimePicker)
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime)
        calendar = Calendar.getInstance()


        btnDateTimePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    val minute = calendar.get(Calendar.MINUTE)

                    val timePickerDialog = TimePickerDialog(this,
                        { _, selectedHour, selectedMinute ->
                            calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                            calendar.set(Calendar.MINUTE, selectedMinute)

                            val selectedDateTime = SimpleDateFormat(
                                "dd/MM/yyyy HH:mm",
                                Locale.getDefault()
                            ).format(calendar.time)
                            tvSelectedDateTime.text = "Recordatorio: $selectedDateTime"
                        }, hour, minute, true)
                    timePickerDialog.show()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        listView.adapter = adapter
        registerForContextMenu(listView)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = options[position]
            showAlertDialog(selectedItem)
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }

    private fun showAlertDialog(option: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿$option?")

        if (option == options.last()) {
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
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("EASTER EGG")
        menu.add(Menu.NONE, 1, Menu.NONE, "SECRETO")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            1 -> {
                Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutUs -> {
                val popup = PopupMenu(this, findViewById<View>(R.id.aboutUs))
                popup.inflate(R.menu.popup_menu)
                popup.setOnMenuItemClickListener(this)
                popup.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutUsPopup -> {
                val intent = Intent(this, AboutUs::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }


}