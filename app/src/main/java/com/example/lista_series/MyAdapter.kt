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

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    lateinit var Intro : EditText
    val titulos = arrayOf("Steins;Gate", "Mushoku Tensei", "Re;Zero", "Tengen Toppa Gurren Laggan")
    val detalles = arrayOf("Ta chido", "Compralo", "10/10", "Obra Maestra")
    val images = intArrayOf(R.drawable.sg,
        R.drawable.mt,
        R.drawable.rz,
        R.drawable.ttgl)

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


}