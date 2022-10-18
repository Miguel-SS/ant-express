package com.example.laboratorio03


import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.laboratorio03.Logica.Reserva


class DetalleSolicitud : AppCompatActivity() {
    private var fullname: EditText? = null
    private var currentAddress: EditText? = null
    private var emailAddress: EditText? = null
    private var phoneNumber: EditText? = null
    private var applingPosition: EditText? = null
    private var startDate: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_solicitud)
        fullname = findViewById(R.id.edtName)
        currentAddress = findViewById(R.id.edtAddress)
        emailAddress = findViewById(R.id.edtEmail)
        phoneNumber = findViewById(R.id.edtPhone)
        applingPosition = findViewById(R.id.edtPosition)
        startDate = findViewById(R.id.edtStart)
        loadData()
    }

    private fun loadData() {
        val reservaSelec = intent.getSerializableExtra("ReservaSelec") as Reserva?
        if (reservaSelec != null) {
            fullname!!.setText(reservaSelec.getUsuario()!!.getnombreCompleto())
            fullname!!.isEnabled = false
            fullname!!.isFocusable = false
            //currentAddress!!.setText(reservaSelec.getVuelo()!!.getRuta()!!.getRuta())
            currentAddress!!.isFocusable = false
            //emailAddress!!.setText(reservaSelec.getVuelo()!!.getHorario()!!.getHoraSalida()+"-"+reservaSelec.getVuelo()!!.getHorario()!!.getHoraLlegada())
            emailAddress!!.isEnabled = false
            emailAddress!!.isFocusable = false
            //phoneNumber!!.setText(reservaSelec.getVuelo()!!.getRuta()!!.getDuracion())
            phoneNumber!!.isEnabled = false
            phoneNumber!!.isFocusable = false
            //applingPosition!!.setText(reservaSelec.getVuelo()!!.getRuta()!!.getPorcentaje().toString()+"%")
            applingPosition!!.isEnabled = false
            applingPosition!!.isFocusable = false
            //startDate!!.setText(reservaSelec.getVuelo()!!.getAvion()!!.getModelo())
            startDate!!.isEnabled = false
            startDate!!.isFocusable = false
        }
    }
}