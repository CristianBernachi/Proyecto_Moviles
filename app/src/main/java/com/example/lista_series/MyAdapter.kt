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
    val DetallesAux = ArrayList<String>()
    val ImagesAux = ArrayList<Int>()

    init {
        TituloAux.addAll(listOf("Steins;Gate", "Mushoku Tensei", "Re;Zero", "Tengen Toppa Gurren Laggan"))
        DetallesAux.addAll(listOf("Ta chido", "Compralo", "10/10", "Obra Maestra"))
        ImagesAux.addAll(listOf(
            R.drawable.sg,
            R.drawable.mt,
            R.drawable.rz,
            R.drawable.ttgl))
        titulos.addAll(TituloAux)
        detalles.addAll(DetallesAux)
        images.addAll(ImagesAux)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
      var itemImage : ImageView
      var itemTitle: TextView
      var itemDetail: TextView
      init {
          itemImage = itemView.findViewById(R.id.portada)
          itemTitle = itemView.findViewById(R.id.titulo)
          itemDetail = itemView.findViewById(R.id.details)

      }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val li = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
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

        for (pos in 0 until TituloAux.size) {
            if (TituloAux[pos].contains(busqueda)) {
                filtroT.add(TituloAux[pos])
                filtroD.add(DetallesAux[pos])
                filtroI.add(ImagesAux[pos])
            }
        }

        titulos.clear()
        detalles.clear()
        images.clear()
        titulos.addAll(filtroT)
        detalles.addAll(filtroD)
        images.addAll(filtroI)

        notifyDataSetChanged()
    }


}