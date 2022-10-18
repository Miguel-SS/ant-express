package com.example.laboratorio03.Logica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.laboratorio03.R

class DetalleUsuario : AppCompatActivity() {
    private var fullname: EditText? = null
    private var currentAddress: EditText? = null
    private var emailAddress: EditText? = null
    private var num: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_usuario)
        fullname = findViewById(R.id.edtName)
        currentAddress = findViewById(R.id.edtAddress)
        emailAddress = findViewById(R.id.edtEmail)
        num = findViewById(R.id.edtNumero)
        loadData()
    }

    private fun loadData() {
        val user = intent.getSerializableExtra("Usuario") as Usuario?
        if (user != null) {
            fullname!!.setText(user.getnombreCompleto())
            fullname!!.isEnabled = false
            fullname!!.isFocusable = false
            currentAddress!!.setText(user.getusuario())
            currentAddress!!.isFocusable = false
            emailAddress!!.setText(user.getcorreo())
            emailAddress!!.isEnabled = false
            emailAddress!!.isFocusable = false
            num!!.setText(user.getnum())
            num!!.isEnabled = false
            num!!.isFocusable = false
        }
    }
}