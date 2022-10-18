package com.example.laboratorio03

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.laboratorio03.ListaUsuarios


class MainActivity : AppCompatActivity() {
    private var nombreUsuario: EditText? = null
    private var password: EditText? = null
    private var register: TextView? = null
    private var cambiaClave: TextView? = null
    private var verifi: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nombreUsuario = findViewById<View>(R.id.edt_userName) as EditText
        password = findViewById<View>(R.id.edt_Password1) as EditText
        register = findViewById<View>(R.id.registrame) as TextView

        register!!.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        register!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }

        cambiaClave = findViewById<View>(R.id.cambioContraseña) as TextView
        cambiaClave!!.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        cambiaClave!!.setOnClickListener {
            val intent = Intent(this@MainActivity, CambiarClaveLogin::class.java)
            startActivity(intent)
        }

        verifi = findViewById<View>(R.id.btn_veri) as Button
        verifi!!.setOnClickListener {
            val intent = Intent(this@MainActivity, Verificacion::class.java)
            startActivity(intent)
        }


    }
    fun login(view: View?) {
        val usuarios: ListaUsuarios = ListaUsuarios.instanceListaUsuarios!!
        val posibleUsuario = usuarios.getUser(nombreUsuario!!.text.toString())
        val response = ListaUsuarios.instanceListaUsuarios!!.validaUsuario(nombreUsuario!!.text.toString(), password!!.text.toString())
        if (response != null) {
            val intent = Intent(this, Intermedia::class.java)
            intent.putExtra("UsuarioR", response)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario incorrecto!", Toast.LENGTH_LONG).show()
        }
    }
}
