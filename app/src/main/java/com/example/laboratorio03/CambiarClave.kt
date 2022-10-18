package com.example.laboratorio03


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.laboratorio03.Logica.Usuario


class CambiarClave : AppCompatActivity() {
    private var title: TextView? = null
    private var claveActual: EditText? = null
    private var nuevaClave: EditText? = null
    private var nuevaClave2: EditText? = null
    private var user: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_clave)
        claveActual = findViewById(R.id.edt_cambioClave_userName)
        nuevaClave = findViewById(R.id.edt_cambioClave_Password1)
        nuevaClave2 = findViewById(R.id.edt_cambioClave_Password2)
        user = intent.getSerializableExtra("Usuario") as Usuario?
        title = findViewById(R.id.txtV_cambioClave)
        title?.setText(title?.getText().toString() + " de " + user!!.getusuario().uppercase())
    }

    fun cambiaClave(view: View?) {
        if (nuevaClave!!.text.toString() == nuevaClave2!!.text.toString()) {
            if (user!!.getpassword() == claveActual!!.text.toString()) {
                Toast.makeText(this, "Se actualizo la clave con éxito", Toast.LENGTH_LONG).show()
                ListaUsuarios.instanceListaUsuarios?.changePassword(user, nuevaClave!!.text.toString())
                val intent = Intent(this, Intermedia::class.java)
                intent.putExtra("Usuario", user)
                startActivity(intent)
            } else {
                claveActual!!.error = "No coincide con la clave Actual"
                claveActual!!.requestFocus()
            }
        } else {
            nuevaClave!!.error = "No coincide"
            nuevaClave!!.requestFocus()
            nuevaClave2!!.error = "No coincide"
        }
    }
}
