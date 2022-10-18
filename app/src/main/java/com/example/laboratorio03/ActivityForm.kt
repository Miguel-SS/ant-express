package com.example.laboratorio03


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.laboratorio03.Logica.Reserva
import com.example.laboratorio03.Logica.Vuelo
import java.util.*


class ActivityForm : AppCompatActivity() {
    private var firstName: EditText? = null
    private var lastName: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagar_boleto)
    }


}
