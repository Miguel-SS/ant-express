package com.example.laboratorio03.Logica

import java.io.Serializable

class Avion: Serializable {

    private var id_avion: Int = 0
    private var annio: Int = 0
    private var modelo: String? = null
    private var marca: String? = null
    private var cant_pasajeros: Int = 0
    private var cant_filas: Int = 0
    private var cant_asiento_fila: Int = 0

    constructor(){

     this.modelo = ""
     this.marca = ""
    }

    constructor(
        id_avion: Int,
        annio: Int,
        modelo: String?,
        marca: String?,
        cant_pasajeros: Int,
        cant_filas: Int,
        cant_asiento_fila: Int
    ) {
        this.id_avion = id_avion
        this.annio = annio
        this.modelo = modelo
        this.marca = marca
        this.cant_pasajeros = cant_pasajeros
        this.cant_filas = cant_filas
        this.cant_asiento_fila = cant_asiento_fila
    }

    fun getIdAvion(): Int {
        return id_avion
    }
    fun getAnnio(): Int {
        return annio
    }
    fun getModelo(): String? {
        return modelo
    }
    fun getMarca(): String? {
        return marca
    }
    fun getCantPasajeros(): Int{
        return cant_pasajeros
    }
    fun getCantFilas(): Int{
        return cant_filas
    }
    fun getCantAsientoFila(): Int{
        return cant_asiento_fila
    }

    fun setAnnio(annio: Int){
        this.annio = annio
    }

    fun setModelo(model: String?){
        this.modelo = model
    }

    fun setMarca(marca: String?){
        this.marca = marca
    }
    fun setCantPasajeros(pasajeros: Int){
        this.cant_pasajeros = pasajeros
    }
    fun setCantFilas(filas: Int){
       this.cant_filas = filas
    }
    fun setCantAsientoFila(asientoFilas: Int) {
        this.cant_asiento_fila = asientoFilas
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "Avion(id_avion=$id_avion, annio=$annio, modelo=$modelo, marca=$marca, cant_pasajeros=$cant_pasajeros, cant_filas=$cant_filas, cant_asiento_fila=$cant_asiento_fila)"
    }


}