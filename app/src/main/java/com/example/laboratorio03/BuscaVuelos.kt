package com.example.laboratorio03

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.laboratorio03.Logica.Usuario
import com.example.laboratorio03.Logica.Vuelo
import com.google.android.material.navigation.NavigationView
import java.util.*

class BuscaVuelos : AppCompatActivity(){


    private var spinnerRutas: Spinner? = null
    private var spinnerPasajeros: Spinner? = null
    private var date: TextView? = null
    private var date2: TextView? = null
    private var lista : ArrayList<Vuelo?>? = null
    private var vuel: Button? = null
    private var User: Usuario? = null
    private var reserv: Button? = null
    private var checkin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca_vuelos)
        date = findViewById(R.id.editTextDate)
        date?.setOnClickListener(View.OnClickListener { initCalendar() })
        spinnerRutas = findViewById(R.id.id_spin_rutas)
        spinnerPasajeros = findViewById(R.id.cantPasajeros)
        cargarSpinners()

        User = intent.getSerializableExtra("UsuarioR") as Usuario?

        vuel = findViewById<View>(R.id.vuelos) as Button
        vuel!!.setOnClickListener {
            val intent = Intent(this@BuscaVuelos, BuscaVuelos::class.java)
            startActivity(intent)
        }

        reserv = findViewById<View>(R.id.misreservas) as Button
        reserv!!.setOnClickListener {
            val intent = Intent(this@BuscaVuelos, Reservas::class.java)
            intent.putExtra("UsuarioR", User)
            startActivity(intent)
        }

        checkin = findViewById<View>(R.id.checkin) as Button
        checkin!!.setOnClickListener {
            val intent = Intent(this@BuscaVuelos, Check::class.java)
            intent.putExtra("UsuarioR", User)
            startActivity(intent)
        }
    }

    private fun cargarSpinners() {
        val adaptadorRutas = ArrayAdapter.createFromResource(
            this,
            R.array.ruta_arrays,
            android.R.layout.simple_list_item_1
        )
        adaptadorRutas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRutas!!.adapter = adaptadorRutas
        val adaptadorPasajeros = ArrayAdapter.createFromResource(
            this,
            R.array.pasajeros_arrays,
            android.R.layout.simple_list_item_1
        )
        adaptadorPasajeros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPasajeros!!.adapter = adaptadorPasajeros
    }

    fun buscar(view: View?) {
        val intent = Intent(this, Vuelos::class.java)
        val vuelo = spinnerRutas!!.selectedItem.toString()
        val pasaj = spinnerPasajeros!!.selectedItem.toString()
        val pasaje: Int? = pasaj.toInt()
        lista = ModelData.instanceModelData!!.buscarVuelo(vuelo)
        intent.putExtra("UsuarioR", User)
        intent.putExtra("Pasajeros",pasaje)
        Log.i("siiiBuscaVuelosUsuario2",User?.getusuario().toString())
        intent.putExtra("ListaVuelos", lista)
        startActivity(intent)
    }

    internal inner class myDateSetListener : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
            var month = month
            month++
            val date_ = "$dayOfMonth/$month/$year"
           date!!.text = date_
        }
    }

    private fun initCalendar() {
        val cal = Calendar.getInstance()
        val dia = cal[Calendar.DAY_OF_MONTH]
        val mes = cal[Calendar.MONTH]
        val anio = cal[Calendar.YEAR]
        val dialog = DatePickerDialog(
            this,
            android.R.style.Theme_Holo_Dialog_MinWidth, myDateSetListener(),
            anio, mes, dia
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }



}