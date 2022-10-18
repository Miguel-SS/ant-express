package com.example.laboratorio03.Logica

import java.io.Serializable

class Servicio: Serializable {
    private var id_servicio = 0
    private var nombre: String = ""
    private var tipo: String = ""
    private var descripcion: String = ""
    private var costo = 0
    private var icon: String? = ""
    private var calificacion: Double = 0.0


    constructor(id_servicio: Int, nombre: String, tipo: String, descripcion: String, costo: Int, calificacion: Double, icon: String?) {
        this.id_servicio = id_servicio
        this.nombre = nombre
        this.tipo = tipo
        this.descripcion = descripcion
        this.costo = costo
        this.calificacion = calificacion
        this.icon = icon
    }

    constructor()

    fun getIdServicio(): Int {
        return id_servicio
    }

    fun getNombre(): String{
        return nombre
    }

    fun getTipo(): String {
        return tipo
    }

    fun getDescripcion(): String{
        return descripcion
    }

    fun getCalificacion(): Double {
        return calificacion
    }

    fun getCosto(): Int {
        return costo
    }

    fun getIcon(): String? {
        return icon
    }

    override fun toString(): String {
        return "Servicio: $id_servicio, $nombre, $tipo, $descripcion, $costo"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}