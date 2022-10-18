package com.example.laboratorio03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio03.Logica.Vuelo
import java.util.*


class AdaptadorVuelos(
    listSolicitudes: ArrayList<Vuelo?>,
    listener: SolicitudesAdapterListener
) :
    RecyclerView.Adapter<AdaptadorVuelos.ViewHolderSolicitud>(), Filterable {
    private var vuelos: Vuelo? = null
    var listSolicitudes: MutableList<Vuelo?>
    private var filterVuelos: MutableList<Vuelo?>
    private val listener: SolicitudesAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSolicitud {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolderSolicitud(vista)
    }

    override fun onBindViewHolder(holder: ViewHolderSolicitud, position: Int) {
        val vuelo = filterVuelos[position]
        holder.nombre.text = vuelo!!.getRuta()!!.getRuta() + " " + vuelo.getHorario()!!.getHoraSalida()+ " -> " + vuelo.getHorario()!!.getHoraLlegada()
        holder.direccion.text = vuelo.getRuta()!!.getDuracion() + " - Sin escalas "
        holder.posicion.text = vuelo.getAvion()!!.getModelo()
        holder.precio.text = "   $" + vuelo.getPrecio().toString()
    }

    override fun getItemCount(): Int {
        return filterVuelos.size
    }

    interface SolicitudesAdapterListener {
        fun onContactSelected(solid: Vuelo?)
    }

    inner class ViewHolderSolicitud(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var direccion: TextView
        var posicion: TextView
        var precio: TextView
        var viewDelete: RelativeLayout
        var viewItem: RelativeLayout
        var editItem: RelativeLayout

        init {
            nombre = itemView.findViewById(R.id.titleName)
            direccion = itemView.findViewById(R.id.titleAddress)
            posicion = itemView.findViewById(R.id.titlePosition)
            precio = itemView.findViewById(R.id.precioVuelo)
            viewDelete = itemView.findViewById(R.id.view_background_delete)
            viewItem = itemView.findViewById(R.id.view_foreground)
            editItem = itemView.findViewById(R.id.view_background_edit)
            itemView.setOnClickListener { listener.onContactSelected(filterVuelos[adapterPosition]) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                filterVuelos = if (charString.isEmpty()) {
                    listSolicitudes
                } else {
                    val filteredList: MutableList<Vuelo?> = ArrayList()
                    for (row in listSolicitudes) {
                        // filter use three parameters
                        if (row!!.getRuta()!!.getRuta()!!.toLowerCase()
                                .contains(charString.toLowerCase()) || row.getRuta()!!.getRuta()!!.toLowerCase()
                                .contains(charString.toLowerCase())

                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filterVuelos
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filterVuelos = filterResults.values as ArrayList<Vuelo?>
                notifyDataSetChanged()
            }
        }
    }


    fun removeItem(position: Int) {
        vuelos = filterVuelos.removeAt(position)
        val iter = listSolicitudes.iterator()
        while (iter.hasNext()) {
            val aux = iter.next()
            if (vuelos == aux) iter.remove()
        }
        // notify item removed
        notifyItemRemoved(position)
    }

    fun restoreItem(position: Int) {
        if (filterVuelos.size == listSolicitudes.size) {
            filterVuelos.add(position, vuelos)
        } else {
            filterVuelos.add(position, vuelos)
            listSolicitudes.add(vuelos)
        }
        notifyDataSetChanged()
        // notify item added by position
        notifyItemInserted(position)
    }
    fun getSwipedItem(index: Int): Vuelo? {
        return if (listSolicitudes.size == filterVuelos.size) { //not filtered yet
            listSolicitudes[index]
        } else {
            filterVuelos[index]
        }
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (listSolicitudes.size == filterVuelos.size) { // without filter
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(listSolicitudes, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(listSolicitudes, i, i - 1)
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(filterVuelos, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(filterVuelos, i, i - 1)
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    init {
        this.listSolicitudes = listSolicitudes
        filterVuelos = listSolicitudes
        this.listener = listener
    }
}



