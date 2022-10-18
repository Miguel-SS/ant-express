package com.example.laboratorio03.Logica

import java.io.Serializable

class Horario: Serializable {

    private var id_horario = 0
    private var hora_salida: String? = null
    private var hora_llegada: String? = null

    constructor(){
        this.hora_llegada = ""
        this.hora_salida = ""
    }

    constructor(id_horario: Int, hora_salida: String?, hora_llegada: String?) {
        this.id_horario = id_horario
        this.hora_salida = hora_salida
        this.hora_llegada = hora_llegada
    }

    fun getIdHorario(): Int{
        return id_horario
    }

    fun getHoraSalida(): String?{
        return hora_salida
    }

    fun getHoraLlegada(): String?{
        return hora_llegada
    }

    fun setHoraSalida(hora_salida: String?){
        this.hora_salida = hora_salida
    }

    fun setHoraLlegada(hora_llegada: String?){
        this.hora_llegada = hora_llegada
    }

    override fun toString(): String {
        return "Horario(id_horario=$id_horario, hora_salida=$hora_salida, hora_llegada=$hora_llegada)"
    }


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}