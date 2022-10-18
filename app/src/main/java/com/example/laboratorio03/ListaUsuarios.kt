package com.example.laboratorio03

import com.example.laboratorio03.Logica.Usuario
import java.util.*


class ListaUsuarios {
    var usuarios: ArrayList<Usuario>
        private set

    private constructor(usuarios: ArrayList<Usuario>) {
        this.usuarios = usuarios
    }

    private constructor() {
        usuarios = ArrayList()
        usuarios.add(Usuario("Pamela Collado","Pame", "p.collado20@gmail.com","89112124", "1234"))
        usuarios.add(Usuario("Nicole Millán","Nico", "nicolemillan06@gmail.com", "83097324","1234"))
    }

    fun addUsuario(user: Usuario) {
        usuarios.add(user)
    }

    fun getUser(user: String): Usuario? {
        var u: Usuario? = null
        for (item in usuarios) {
            if ( item.getusuario() == user ) {
                u = item
            }
        }
        return u
    }

    fun getUsersList():ArrayList<Usuario>{
        return usuarios
    }

    fun validaUsuario(user: String, pass: String): Usuario? {
        for (u in usuarios) {
            if (u.getusuario().equals(user) && u.getpassword().equals(pass)) {
                return u
            }
        }
        return null
    }

    fun changePassword(user: Usuario?, newPassword: String?): Boolean {
        for (u in usuarios) {
            if (u.equals(user)) {
                u.setpassword(newPassword!!)
                return true
            }
        }
        return false
    }

    companion object {
        private var instance: ListaUsuarios? = null
        val instanceListaUsuarios: ListaUsuarios?
            get() {
                if (instance != null) {
                    return instance
                }
                instance = ListaUsuarios()
                return instance
            }
    }
}
