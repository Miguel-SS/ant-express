package com.example.laboratorio03.Logica

import com.example.laboratorio03.Logica.Usuario
import com.example.laboratorio03.Logica.Vuelo
import java.io.Serializable

class Reserva: Serializable {

    private var user: Usuario? = null
    private var servicio: Servicio? = null

    constructor( user: Usuario?, servicio: Servicio?) {

        this.user = user
        this.servicio = servicio
    }

    constructor()


    fun getUsuario(): Usuario? {
        return user
    }

    fun getServicio(): Servicio? {
        return servicio
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return "Reserva(user=$user, vuelo=$servicio)"
    }


}