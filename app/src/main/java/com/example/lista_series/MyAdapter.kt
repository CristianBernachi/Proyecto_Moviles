package com.example.lista_series

import android.view.*
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MyAdapter(private val seriesList: MutableList<Series>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var filteredList: MutableList<Series> = seriesList
    private var itemClickListener: OnItemClickListener? = null



    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.portada)
            itemTitle = itemView.findViewById(R.id.titulo)
            itemDetail = itemView.findViewById(R.id.details)

        }


        fun bind(item: Int, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }

        fun render(seriesModel: Series) {
            itemTitle.text = seriesModel.titulo
            itemDetail.text = seriesModel.detalles
            itemImage.setImageResource(seriesModel.imagen)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(li)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.render(item)
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.añadirB)
        checkBox.isChecked = item.fav
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.fav = isChecked
        }
        itemClickListener?.let { holder.bind(position, it) }

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun filter(text: String){
        val busqueda = text.lowercase(Locale.getDefault())

        filteredList = if (busqueda.isNotEmpty()) (
            seriesList.filter { series ->
                series.titulo.lowercase(Locale.getDefault()).contains(busqueda)
            }
        ) as MutableList<Series>
        else {
            seriesList
        }

        notifyDataSetChanged()

    }

    fun filterStatus(status: Int) {

        filteredList = if(status!=0)(

                seriesList.filter { it.status == status }

                    ) as MutableList<Series>

        else {
            seriesList
        }

        notifyDataSetChanged()
    }

    fun filterFavorites(onlyFavorites: Boolean) {
        filteredList = if (onlyFavorites) {
            seriesList.filter { it.fav }.toMutableList()
        } else {
            seriesList.toMutableList()
        }

        notifyDataSetChanged()
    }

}
/*
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

 */


    /*val titulos = ArrayList<String>() //Datos cambiados a listas para facilitar la busqueda
   val detalles = ArrayList<String>()
   val images = ArrayList<Int>()
   val TituloAux = ArrayList<String>()
   val DetalleAux = ArrayList<String>()
   val ImageAux = ArrayList<Int>()//Auxiliares para cambiar los arreglos por los filtrados


   init {//Se inicializan los valores con los que se va a cargar el Recyclerviw
       TituloAux.addAll(
           listOf(
               "Breaking Bad",
               "Mushoku Tensei",
               "Re;Zero",
               "Steins;Gate",
               "Tengen Toppa Gurren Laggan"

           )
       )
       DetalleAux.addAll(
           listOf(
               "Jesse",
               "Compralo",
               "Ta chido",
                  "10/10",
           "Obra Maestra"

           )
       )
       ImageAux.addAll(
           listOf(
               R.drawable.bb,
               R.drawable.mt,
               R.drawable.rz,
               R.drawable.sg,
               R.drawable.ttgl

           )
       )
       titulos.addAll(TituloAux)
       detalles.addAll(DetalleAux)
       images.addAll(ImageAux)
   }

       fun filterFav() {

        filteredList = seriesList.filter { it.fav == true } as MutableList<Series>

        notifyDataSetChanged()
    }


   */
