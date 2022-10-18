package com.example.laboratorio03.Logica

import java.io.Serializable
import java.util.ArrayList


class Usuario : Serializable {
    private var nombreCompleto: String
    private var usuario: String
    private var correo: String
    private var numero: String
    private var password: String

    constructor(nombreCompleto: String, usuario: String, correo: String, numero: String, password: String) {
        this.nombreCompleto = nombreCompleto
        this.usuario = usuario
        this.correo = correo
        this.numero = numero
        this.password = password }

    fun getnombreCompleto() : String{
        return nombreCompleto
    }

    fun getusuario(): String{
        return usuario
    }

    fun getcorreo(): String{
        return correo
    }

    fun getpassword(): String{
        return password
    }

    fun setnombreCompleto(nombreCompleto: String){
        this.nombreCompleto = nombreCompleto
    }

    fun getnum(): String{
        return numero
    }

    fun setnum(numero: String){
        this.numero = numero
    }


    fun setusuario(usuario: String){
        this.usuario = usuario
    }

    fun setcorreo(correo: String){
        this.correo = correo
    }

    fun setpassword(password: String){
        this.password = password
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as Usuario
        return usuario == user.usuario && password == user.password
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "Usuario(nombreCompleto='$nombreCompleto', usuario='$usuario', correo='$correo', password='$password')"
    }

}

