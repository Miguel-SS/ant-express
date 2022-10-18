package com.example.laboratorio03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Inicio : AppCompatActivity() {

    var inicioB: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        inicioB = findViewById<View>(R.id.buttonInicio) as Button
        inicioB!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}