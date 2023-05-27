package com.example.lista_series

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    val titulos = ArrayList<String>() //Datos cambiados a listas para facilitar la busqueda
    val detalles = ArrayList<String>()
    val images = ArrayList<Int>()
    val TituloAux = ArrayList<String>()
    val DetalleAux = ArrayList<String>()
    val ImageAux = ArrayList<Int>()//Auxiliares para cambiar los arreglos por los filtrados

    init {//Se inicializan los valores con los que se va a cargar el Recyclerviw
        TituloAux.addAll(
            listOf(
                "Steins;Gate",
                "Mushoku Tensei",
                "Re;Zero",
                "Tengen Toppa Gurren Laggan"
            )
        )
        DetalleAux.addAll(
            listOf(
                "Ta chido",
                "Compralo",
                "10/10",
            "Obra Maestra"
            )
        )
        ImageAux.addAll(
            listOf(
                R.drawable.sg,
                R.drawable.mt,
                R.drawable.rz,
                R.drawable.ttgl
            )
        )
        titulos.addAll(TituloAux)
        detalles.addAll(DetalleAux)
        images.addAll(ImageAux)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {//Se buscan los componentes del recycler para poder modificarlos con los valores antes inicializados
            itemImage = itemView.findViewById(R.id.portada)
            itemTitle = itemView.findViewById(R.id.titulo)
            itemDetail = itemView.findViewById(R.id.details)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(li)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titulos[position]
        holder.itemImage.setImageResource(images[position])
        holder.itemDetail.text = detalles[position]

    }

    override fun getItemCount(): Int {
        return titulos.size
    }

    fun filter(text: String) {
        val busqueda = text
        val filtroT = ArrayList<String>()
        val filtroD = ArrayList<String>()
        val filtroI = ArrayList<Int>()

        for (pos in 0 until TituloAux.size) {//Se busca en el arreglo de los titulos para ver si se encuentra lo buscado
            if (TituloAux[pos].contains(busqueda)) {
                filtroT.add(TituloAux[pos])
                filtroD.add(DetalleAux[pos])
                filtroI.add(ImageAux[pos])
            }
        }

        titulos.clear()//Se limpian los arreglos y se les carga con los nuevos datos filtrados
        detalles.clear()
        images.clear()
        titulos.addAll(filtroT)
        detalles.addAll(filtroD)
        images.addAll(filtroI)

        notifyDataSetChanged() //Funcion para notificar al Recyclerview de los cambios
    }
}