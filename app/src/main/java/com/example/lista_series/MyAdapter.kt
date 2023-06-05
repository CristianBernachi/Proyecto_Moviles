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

    fun filterStatus(status: Int): MutableList<Series> {

        filteredList = if(status!=0)(

                seriesList.filter { it.status == status }

                    ) as MutableList<Series>

        else {
            seriesList
        }

        notifyDataSetChanged()
        return filteredList
    }

}
