package com.example.laboratorio03


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CambiarClaveLogin : AppCompatActivity() {
    private var title: TextView? = null
    private var nombreUsuario: EditText? = null
    private var claveActual: EditText? = null
    private var nuevaClave: EditText? = null
    private var nuevaClave2: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_clave_login)
        nombreUsuario = findViewById(R.id.edt_usuario)
        claveActual = findViewById(R.id.edt_cambioClave_userName)
        nuevaClave = findViewById(R.id.edt_cambioClave_Password1)
        nuevaClave2 = findViewById(R.id.edt_cambioClave_Password2)
        title = findViewById(R.id.txtV_cambioClave)

    }

    fun cambiaClave(view: View?) {
        if (nuevaClave!!.text.toString() == nuevaClave2!!.text.toString()) {
            val lista: ListaUsuarios = ListaUsuarios.instanceListaUsuarios!!
            val usuario = lista.getUser(nombreUsuario!!.text.toString())
            if (lista.validaUsuario(usuario!!.getusuario(),usuario!!.getpassword()) != null && claveActual!!.text.toString() == usuario.getpassword() &&nombreUsuario!!.text.toString().isNotEmpty() &&
                claveActual!!.text.toString().isNotEmpty() && nuevaClave!!.text.toString().isNotEmpty() && nuevaClave2!!.text.toString().isNotEmpty())
                {
                lista.changePassword(usuario, nuevaClave!!.text.toString())
                Toast.makeText(this, "La clave se ha cambiado con éxito", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Complete todos los campos!", Toast.LENGTH_SHORT).show()
            }
        } else {
            nuevaClave!!.error = "No coincide"
            nuevaClave!!.requestFocus()
            nuevaClave2!!.error = "No coincide"
        }
    }
}