package com.example.laboratorio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.laboratorio03.Logica.Usuario

class Verificacion2 : AppCompatActivity() {

    private var verbot: Button? = null
    private var codigo: String? = null
    private var codigo2: EditText? = null
    private var codigo3: String? = null
    private var Use: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion2)
        codigo = intent.getSerializableExtra("Codigo") as String?
        Use = intent.getSerializableExtra("UsuarioR") as Usuario?

        verbot = findViewById<View>(R.id.btnenv) as Button
        codigo2 = findViewById<View>(R.id.edit_Codigo) as EditText

        verbot!!.setOnClickListener {
            codigo3 = codigo2!!.text.toString()
            if(verificar(codigo!!,codigo3!!)){
                val intent = Intent(this, Intermedia::class.java)
                intent.putExtra("UsuarioR", Use)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Código incorrecto, intente nuevamente", Toast.LENGTH_SHORT).show()
            }
         }
    }

    private fun verificar(codi1:String, codi2:String):Boolean{
        if(codi1.equals(codi2))
            return true
        else
            return false
    }
}