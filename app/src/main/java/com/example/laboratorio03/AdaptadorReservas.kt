package com.example.laboratorio03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio03.Logica.Reserva
import java.util.*


class AdaptadorReservas(
    context: Context,
    listSolicitudes: ArrayList<Reserva?>,
    listener: ReservasAdapterListener
) :
    RecyclerView.Adapter<AdaptadorReservas.ViewHolderReserva>(), Filterable {
    private var _solicitudes: Reserva? = null
    var listSolicitudes: MutableList<Reserva?>
    private var filterSolicitudes: MutableList<Reserva?>
    private val listener: ReservasAdapterListener
    private var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReserva {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.items2, parent, false)
        return ViewHolderReserva(vista)
    }

    override fun onBindViewHolder(holder: ViewHolderReserva, position: Int) {
        val solicitud = filterSolicitudes[position]
        holder.ruta.text = ""
        holder.nombreCompleto.text = solicitud!!.getServicio()?.getNombre() + ", " + solicitud.getServicio()?.getDescripcion()
        holder.horario.text = solicitud.getServicio()?.getNombre()
        if(solicitud!!.getServicio()?.getIcon().equals("LIMPIEZA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.limpieza2))
        }else if (solicitud!!.getServicio()?.getIcon().equals("FONTANERIA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.pbb))
        }
        else if (solicitud!!.getServicio()?.getIcon().equals("CARPINTERIA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.carp))
        }
        else if (solicitud!!.getServicio()?.getIcon().equals("ELECTRICISTA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.elec))
        } else if (solicitud!!.getServicio()?.getIcon().equals("MUEBLES")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.mue))
        }
    }

    override fun getItemCount(): Int {
        return filterSolicitudes.size
    }

    interface ReservasAdapterListener {
        fun onContactSelected(solid: Reserva?)
    }

    inner class ViewHolderReserva(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var nombreCompleto: TextView
        var ruta: TextView
        var icon: ImageView
        var horario: TextView
        var viewDelete: RelativeLayout
        var viewItem: RelativeLayout
        var editItem: RelativeLayout

        init {
            nombreCompleto = itemView.findViewById(R.id.titleName)
            ruta = itemView.findViewById(R.id.titleAddress)
            icon = itemView.findViewById(R.id.service_icon)
            horario = itemView.findViewById(R.id.titlePosition)
            viewDelete = itemView.findViewById(R.id.view_background_delete)
            viewItem = itemView.findViewById(R.id.view_foreground)
            editItem = itemView.findViewById(R.id.view_background_edit)
            itemView.setOnClickListener { listener.onContactSelected(filterSolicitudes[adapterPosition]) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                filterSolicitudes = if (charString.isEmpty()) {
                    listSolicitudes
                } else {
                    val filteredList: MutableList<Reserva?> = ArrayList()
                    for (row in listSolicitudes) {
                        // filter use three parameters
                        if (row!!.getServicio()!!.getNombre()!!.toLowerCase()
                                .contains(charString.toLowerCase()) || row.getServicio()!!.getNombre()!!.toLowerCase()
                                .contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filterSolicitudes
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filterSolicitudes = filterResults.values as ArrayList<Reserva?>
                notifyDataSetChanged()
            }
        }
    }


    fun removeItem(position: Int) {
        _solicitudes = filterSolicitudes.removeAt(position)
        val iter = listSolicitudes.iterator()
        while (iter.hasNext()) {
            val aux = iter.next()
            if (_solicitudes == aux) iter.remove()
        }
        // notify item removed
        notifyItemRemoved(position)
    }

    fun restoreItem(position: Int) {
        if (filterSolicitudes.size == listSolicitudes.size) {
            filterSolicitudes.add(position, _solicitudes)
        } else {
            filterSolicitudes.add(position, _solicitudes)
            listSolicitudes.add(_solicitudes)
        }
        notifyDataSetChanged()
        // notify item added by position
        notifyItemInserted(position)
    }
    fun getSwipedItem(index: Int): Reserva? {
        return if (listSolicitudes.size == filterSolicitudes.size) { //not filtered yet
            listSolicitudes[index]
        } else {
            filterSolicitudes[index]
        }
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (listSolicitudes.size == filterSolicitudes.size) { // without filter
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
                    Collections.swap(filterSolicitudes, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(filterSolicitudes, i, i - 1)
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    init {
        this.listSolicitudes = listSolicitudes
        this.context = context
        filterSolicitudes = listSolicitudes
        this.listener = listener
    }
}
