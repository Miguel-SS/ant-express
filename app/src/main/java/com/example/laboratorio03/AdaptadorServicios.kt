package com.example.laboratorio03

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio03.Logica.Servicio
import java.util.*

class AdaptadorServicios(
    context: Context,
    listSolicitudes: ArrayList<Servicio?>,
    listener: SolicitudesAdapterListener
) :
    RecyclerView.Adapter<AdaptadorServicios.ViewHolderSolicitud>(), Filterable {

    private var servicio: Servicio? = null
    var listSolicitudes: MutableList<Servicio?>
    private var filterServicios: MutableList<Servicio?>
    private val listener: SolicitudesAdapterListener
    private var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSolicitud {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolderSolicitud(vista)
    }


    override fun getItemCount(): Int {
        return filterServicios.size
    }

    interface SolicitudesAdapterListener{
        fun onContactSelected(solid: Servicio?)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                filterServicios = if (charString.isEmpty()) {
                    listSolicitudes
                } else {
                    val filteredList: MutableList<Servicio?> = ArrayList()
                    for (row in listSolicitudes) {
                        // filter use three parameters
                        if (row!!.getTipo()!!.toLowerCase()
                                .contains(charString.toLowerCase()) || row.getTipo()!!.toLowerCase()
                                .contains(charString.toLowerCase())

                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filterServicios
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filterServicios = filterResults.values as ArrayList<Servicio?>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolderSolicitud(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var nombre: TextView
        var tipo: TextView
        var calificacion: TextView

        init {
            icon = itemView.findViewById(R.id.service_icon)
            nombre = itemView.findViewById(R.id.titleName)
            tipo = itemView.findViewById(R.id.titleAddress)
            calificacion = itemView.findViewById(R.id.precioVuelo)
            itemView.setOnClickListener { listener.onContactSelected(filterServicios[adapterPosition]) }
        }
    }


    override fun onBindViewHolder(holder: ViewHolderSolicitud, position: Int) {
        val servicio = filterServicios[position]
        holder.nombre.text = servicio!!.getNombre()
        holder.tipo.text = servicio.getTipo()
        holder.calificacion.text = servicio.getCalificacion().toString()
        if(servicio!!.getIcon().equals("LIMPIEZA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.limpieza2))
        }else if (servicio!!.getIcon().equals("FONTANERIA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.pbb))
        }else if (servicio!!.getIcon().equals("CARPINTERIA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.carp))
        }
        else if (servicio!!.getIcon().equals("ELECTRICISTA")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.elec))
        } else if (servicio!!.getIcon().equals("MUEBLES")){
            holder.icon.setImageDrawable(context.resources.getDrawable(R.drawable.mue))
        }
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (listSolicitudes.size == filterServicios.size) { // without filter
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
                    Collections.swap(filterServicios, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(filterServicios, i, i - 1)
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    init {
        this.listSolicitudes = listSolicitudes
        this.context = context
        filterServicios = listSolicitudes
        this.listener = listener
    }

}
