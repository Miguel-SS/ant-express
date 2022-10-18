package com.example.laboratorio03

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.laboratorio03.Logica.Usuario
import com.hbb20.CountryCodePicker
import kotlin.random.Random

class Verificacion : AppCompatActivity() {
    private var ccp: CountryCodePicker? = null
    private var phoneTextView: EditText? = null
    private var sendBtn: Button? = null
    private var txtMobile: String? = null
    private var num: String? = null
    private var txtMessage: String? = null
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion)
        initializeViews()
        //listeners()



        num = Random.nextInt(1238, 9857).toString()
        txtMessage = "SU CÓDIGO DE VERIFICACIÓN ES: " + num

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        sendBtn!!.setOnClickListener {
            try {
                txtMobile = phoneTextView!!.text.toString()
                if(validarDig(txtMobile!!)) {
                val smgr = SmsManager.getDefault()
                smgr.sendTextMessage(txtMobile!!, null, txtMessage!!, null, null)

                usu = traerUsuario(txtMobile!!)
                Toast.makeText(this@Verificacion, "Código de verificación enviado al " + txtMobile, Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, Verificacion2::class.java)
                    intent.putExtra("Codigo", num)
                    intent.putExtra("UsuarioR", usu)
                startActivity(intent)
            }else
                    Toast.makeText(this@Verificacion, "Número incorrecto o no se encuentra registrado", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this@Verificacion,
                    "SMS Falló, Por favor intente nuevamente",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i("SMS", e.toString())
                val intent = Intent(this, Verificacion::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initializeViews() {
        ccp = findViewById<View>(R.id.ccp) as CountryCodePicker
        phoneTextView = findViewById<View>(R.id.editTextPhone) as EditText
        sendBtn = findViewById<View>(R.id.btnSend) as Button
    }

    private fun listeners() {
        sendBtn!!.setOnClickListener { // Get Variable
            val code = ccp!!.selectedCountryCode
            val country = ccp!!.selectedCountryEnglishName
            val number = phoneTextView!!.text.toString()
            val intent = Intent(this,Verificacion2::class.java)
            startActivity(intent)

            // Create Toast
            val context = applicationContext
            val text: CharSequence = "Mensaje enviado al $code $number"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }

    private fun validarDig(num:String):Boolean{
        if(num.length==8){
           for(user in ListaUsuarios.instanceListaUsuarios!!.getUsersList()){
               if(num == user.getnum())
                   return true
           }
        }
        return false
    }

    private fun traerUsuario(num:String): Usuario? {
        if(num.length==8){
            for(user in ListaUsuarios.instanceListaUsuarios!!.getUsersList()){
                if(num == user.getnum())
                    return user
            }
        }
        return null
    }


}