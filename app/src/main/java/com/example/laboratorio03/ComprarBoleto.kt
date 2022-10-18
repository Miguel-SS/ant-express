package com.example.laboratorio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio03.Logica.Servicio
import com.example.laboratorio03.Logica.Usuario
import com.example.laboratorio03.Logica.Vuelo
import java.util.ArrayList

class ComprarBoleto : AppCompatActivity() {

    var continuarB: Button? = null
    var imgServicio: ImageView? = null
    var nombreServicio: TextView? = null
    var categoriaServicio: TextView? = null
    var calificacionServicio: ImageView? = null
    var descripcionServicio: TextView? = null
    var prec: TextView? = null
    var servicio: Servicio? = null
    var User: Usuario? = null
    var precioTot: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprar_boleto)
        servicio = intent.getSerializableExtra("ServicioSeleccionado") as Servicio?
        init(servicio)
        continuarB!!.setOnClickListener {

            User = intent.getSerializableExtra("UsuarioR") as Usuario?
            precioTot = intent.getSerializableExtra("PrecioTotal") as Int?
            val intent = Intent(this, PagarBoleto::class.java)
            intent.putExtra("UsuarioR", User)
            intent.putExtra("ServicioSeleccionado", servicio)
            intent.putExtra("PrecioTotal", precioTot)
            startActivity(intent)

        }
    }

    fun init(serv: Servicio?){
        continuarB = findViewById<View>(R.id.btn_continuar) as Button
        imgServicio = findViewById<View>(R.id.imageServici) as ImageView
        nombreServicio = findViewById<View>(R.id.nombreServicio) as TextView
        categoriaServicio = findViewById<View>(R.id.categoriaServicio) as TextView
        calificacionServicio = findViewById<View>(R.id.calificacionServicio) as ImageView
        descripcionServicio = findViewById<View>(R.id.descripcionServicio) as TextView
        prec = findViewById<View>(R.id.preSer) as TextView

        if(serv!!.getIcon().equals("LIMPIEZA")){
            imgServicio!!.setImageDrawable(resources.getDrawable(R.drawable.limpieza2))
        } else if (serv!!.getIcon().equals("FONTANERIA")){
            imgServicio!!.setImageDrawable(resources.getDrawable(R.drawable.pbb))
        } else if (serv!!.getIcon().equals("CARPINTERIA")){
            imgServicio!!.setImageDrawable(resources.getDrawable(R.drawable.carp))
        } else if (serv!!.getIcon().equals("ELECTRICISTA")){
            imgServicio!!.setImageDrawable(resources.getDrawable(R.drawable.elec))
        } else if (serv!!.getIcon().equals("MUEBLES")){
            imgServicio!!.setImageDrawable(resources.getDrawable(R.drawable.mue))
        }

        nombreServicio!!.setText(serv!!.getNombre())
        categoriaServicio!!.setText(serv!!.getTipo())
        descripcionServicio!!.setText(serv!!.getDescripcion())
        prec!!.setText("Costo del servicio: ₡"+ serv!!.getCosto().toString())
    }

}