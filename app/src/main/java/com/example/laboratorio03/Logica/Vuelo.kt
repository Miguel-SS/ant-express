package com.example.laboratorio03.Logica

import java.io.Serializable

class Vuelo: Serializable {

    private var id_vuelo = 0
    private var avion: Avion? = null
    private var ruta: Ruta? = null
    private var horario: Horario? = null
    private var precio = 0


    constructor(id_vuelo: Int, avion: Avion?, ruta: Ruta?, horario: Horario?, precio: Int) {
        this.id_vuelo = id_vuelo
        this.avion = avion
        this.ruta = ruta
        this.horario = horario
        this.precio = precio
    }

    constructor()

    fun getIdVuelo(): Int {
        return id_vuelo
    }

    fun getAvion(): Avion? {
        return avion
    }

    fun getRuta(): Ruta? {
        return ruta
    }

    fun getHorario(): Horario?{
        return horario
    }

    fun getPrecio(): Int {
        return precio
    }

    override fun toString(): String {
        return "Vuelo(id_vuelo=$id_vuelo, avion=$avion, ruta=$ruta, horario=$horario, precio=$precio)"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}