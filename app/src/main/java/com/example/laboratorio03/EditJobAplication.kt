package com.example.laboratorio03

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditJobAplication : AppCompatActivity() {
    private var etFirstname: EditText? = null
    private var etLastname: EditText? = null
    private var etstreetAddress1: EditText? = null
    private var etstreetAddress2: EditText? = null
    private var etCity: EditText? = null
    private var etState: EditText? = null
    private var etPostal: EditText? = null
    private var etAreaCode: EditText? = null
    private var etEmail: EditText? = null
    private var etPhone: EditText? = null
    private var etDate: EditText? = null
    private var spinnerCountry: Spinner? = null
    private var spinnerPosition: Spinner? = null
    private var data : Reserva? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_job_aplication)
        inicializarAtributos()
        cargarSpinners()
        loadData()
    }

    private fun inicializarAtributos(){
        spinnerCountry = findViewById(R.id.id_spin_country)
        spinnerPosition = findViewById(R.id.id_spin_Position)
        etDate = findViewById<View>(R.id.id_txtV_startDate) as EditText
        etFirstname = findViewById<View>(R.id.id_edt_FirstName) as EditText
        etLastname = findViewById<View>(R.id.id_edt_lastName) as EditText
        etstreetAddress1 = findViewById<View>(R.id.id_edt_streetAdd1) as EditText
        etstreetAddress2 = findViewById<View>(R.id.id_edt_streetAdd2) as EditText
        etCity = findViewById<View>(R.id.id_edt_city) as EditText
        etState = findViewById<View>(R.id.id_edt_state) as EditText
        etPostal = findViewById<View>(R.id.id_edt_postal) as EditText
        etAreaCode = findViewById<View>(R.id.id_edt_AreaCode) as EditText
        etEmail = findViewById<View>(R.id.id_edt_email) as EditText
        etPhone = findViewById<View>(R.id.id_edt_PhoneNumber) as EditText

    }

    private fun cargarSpinners() {
        val adaptadorCountry = ArrayAdapter.createFromResource(
            this,
            R.array.ruta_arrays,
            android.R.layout.simple_list_item_1
        )
        adaptadorCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry!!.adapter = adaptadorCountry
        val adaptadorPosition = ArrayAdapter.createFromResource(
            this,
            R.array.pasajeros_arrays,
            android.R.layout.simple_list_item_1
        )
        adaptadorPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPosition!!.adapter = adaptadorPosition

    }

    private fun buildJobApplication(): Reserva {
        return Reserva(
            etFirstname!!.text.toString(),
            etLastname!!.text.toString(),
            etstreetAddress1!!.text.toString(),
            etstreetAddress2!!.text.toString(),
            etCity!!.text.toString(),
            etState!!.text.toString(),
            etPostal!!.text.toString(),
            spinnerCountry!!.selectedItem.toString(),
            spinnerCountry!!.selectedItemPosition,
            etEmail!!.text.toString(),
            etAreaCode!!.text.toString(),
            etPhone!!.text.toString(),
            spinnerPosition!!.selectedItem.toString(),
            spinnerPosition!!.selectedItemPosition,
            etDate!!.text.toString()
        )
    }

    override fun onKeyDown(keyCode: Int,  event: KeyEvent) : Boolean {
        if((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun loadData() {
        data = intent.getSerializableExtra("Data") as Reserva?

        if(data != null){
            etDate!!.setText(data!!.startDate)
            etFirstname!!.setText(data!!.firstName)
            etLastname!!.setText(data!!.lastName)
            etstreetAddress1!!.setText(data!!.streetAddress1)
            etstreetAddress2!!.setText(data!!.streetAddress2)
            etCity!!.setText(data!!.city)
            etState!!.setText(data!!.state)
            etPostal!!.setText(data!!.postal)
            spinnerCountry!!.setSelection(data!!.countryIndex)
            etAreaCode!!.setText(data!!.areaCode)
            etEmail!!.setText(data!!.email)
            etPhone!!.setText(data!!.phoneNumber)
            spinnerPosition!!.setSelection(data!!.positionIndex)

        }

    }

    fun addJobApplication(v: View?) {
        val position = intent.getSerializableExtra("Position") as Int
  //      ListaJobApplication.instanceListaJobApplication?.editJobApplication(position,buildJobApplication())
        Toast.makeText(this, "Formulario actualizado con éxito!", Toast.LENGTH_SHORT).show()
        finish()
        val intent = Intent(this, Vuelos::class.java)
        startActivity(intent)
    }



}