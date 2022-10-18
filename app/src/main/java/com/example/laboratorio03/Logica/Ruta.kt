package com.example.laboratorio03.Logica

import java.io.Serializable

class Ruta: Serializable {

    private var id_ruta = 0
    private var ruta: String? = null
    private var duracion: String? = null
    private var descuento: String? = null
    private var porcentaje = 0

    constructor(){
        this.ruta= ""
        this.duracion = ""
        this.descuento = ""
    }

    constructor(
        id_ruta: Int,
        ruta: String?,
        duracion: String?,
        descuento: String?,
        porcentaje: Int
    ) {
        this.id_ruta = id_ruta
        this.ruta = ruta
        this.duracion = duracion
        this.descuento = descuento
        this.porcentaje = porcentaje
    }

    fun getIdRuta(): Int {
        return id_ruta
    }
    fun getRuta(): String? {
        return ruta
    }
    fun getDuracion(): String? {
        return duracion
    }
    fun getDescuento(): String? {
        return descuento
    }
    fun getPorcentaje(): Int{
        return porcentaje
    }


    fun setRuta(ruta: String?){
        this.ruta = ruta
    }

    fun setDuracion(duracion: String?){
        this.duracion = duracion
    }

    fun setDescuento(duracion: String?){
        this.duracion = duracion
    }
    fun setPorcentaje(porcentaje: Int){
        this.porcentaje = porcentaje
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return "Ruta(id_ruta=$id_ruta, ruta=$ruta, duracion=$duracion, descuento=$descuento, porcentaje=$porcentaje)"
    }


}