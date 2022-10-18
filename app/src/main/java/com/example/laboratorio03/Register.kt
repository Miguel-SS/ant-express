package com.example.laboratorio03


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.laboratorio03.Logica.Usuario


class Register : AppCompatActivity() {
    private var param1: String? = null
    private var param2: String? = null
    private var nombreCompleto: EditText? = null
    private var user: EditText? = null
    private var email: EditText? = null
    private var nume: EditText? = null
    private var password: EditText? = null
    private var password2: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        nombreCompleto = findViewById(R.id.edt_fullName)
        user = findViewById(R.id.edt_userName)
        email = findViewById(R.id.edt_email)
        nume = findViewById(R.id.edt_num)
        password = findViewById(R.id.edt_Password1)
        password2 = findViewById(R.id.edt_Password2)
    }

    fun registrar(view: View?) {
        val nombrecompleto = nombreCompleto!!.text.toString()
        val user_ = user!!.text.toString()
        val email_ = email!!.text.toString()
        val nume_ = nume!!.text.toString()
        val password_ = password!!.text.toString()
        val password2_ = password2!!.text.toString()
        nombreCompleto!!.error = null
        user!!.error = null
        email!!.error = null
        nume!!.error = null
        password!!.error = null
        password2!!.error = null

        if (nombrecompleto.isEmpty() || user_.isEmpty() || email_.isEmpty() || nume_.isEmpty() || password_.isEmpty() || password2_.isEmpty()) {
            Toast.makeText(this, "Campos incompletos", Toast.LENGTH_LONG).show()
        } else {
            if (password_ == password2_) {
                ListaUsuarios.instanceListaUsuarios?.addUsuario(Usuario(nombrecompleto,user_, email_, nume_,password2_))
                Toast.makeText(this, "Usuario registrado con éxito!", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                password!!.error = "No coincide"
                password2!!.error = "No coincide"
                password!!.requestFocus()
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
        }



    }
}

