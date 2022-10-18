package com.example.laboratorio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.laboratorio03.Logica.Reserva
import com.example.laboratorio03.Logica.Servicio
import com.example.laboratorio03.Logica.Usuario
import com.example.laboratorio03.Logica.Vuelo
import java.util.ArrayList

class PagarBoleto : AppCompatActivity() {

    var pagarB: Button? = null
    var servicio: Servicio? = null
    var User: Usuario? = null
    var precioTotal: Int? = null
    var precio: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagar_boleto)
        precio = findViewById(R.id.precio)
        User = intent.getSerializableExtra("UsuarioR") as Usuario?
        servicio = intent.getSerializableExtra("ServicioSeleccionado") as Servicio?
        precioTotal = intent.getSerializableExtra("PrecioTotal") as Int?
        precio!!.setText("Precio a pagar: ₡"+precioTotal!!.toString())
        var rlistaReservas : ArrayList<Reserva>?

        pagarB = findViewById<View>(R.id.btn_pagar) as Button
        pagarB!!.setOnClickListener {
            addReserva()
            val intent = Intent(this, Intermedia::class.java)
            intent.putExtra("UsuarioR", User)
            intent.putExtra("ServicioSeleccionado", servicio)
            startActivity(intent)
            Log.i("siiiIngresa",
                ListaMisReservas.instanceListaMisReservas?.lista.toString())

            val rlistaReservas = ListaMisReservas.instanceListaMisReservas?.lista.toString()
            intent.putExtra("listaReservas", rlistaReservas)
        }
    }

    fun addReserva() {
        ListaMisReservas.instanceListaMisReservas?.addMiReserva(buildMisReservas())
        Log.i("siiiPagarReserva", ListaMisReservas.instanceListaMisReservas?.lista.toString())
        Toast.makeText(this, "Compra realizada!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun buildMisReservas(): Reserva {
        return Reserva(User,servicio)
    }
}