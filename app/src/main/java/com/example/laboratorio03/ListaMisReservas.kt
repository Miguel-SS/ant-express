package com.example.laboratorio03

import com.example.laboratorio03.Logica.Reserva
import com.example.laboratorio03.Logica.Vuelo
import java.util.ArrayList

class ListaMisReservas {
        var lista: ArrayList<Reserva?>
            private set

        private constructor(Reserva: ArrayList<Reserva?>) {
            this.lista = Reserva
        }

        private constructor() {
            lista = ArrayList()
            //lista.add(JobApplication("Monica", "Arce", "Residencial Roemy", "Casa 5-A", "Merecedes Norte", "Heredia", "40205", "Costa Rica", 48, "monica@gmail.com", "506","89112124","Manager",1,"10/06/2021"))

        }
        fun addMiReserva(res: Reserva) {
            lista.add(res)
        }

        fun getJobApplication(position : Int): Reserva? {
            return lista.get(position)
        }

    fun buscarReserva(usuario: String?): ArrayList<Reserva?>? {
        var res: ArrayList<Reserva?>? = ArrayList<Reserva?>()
        for (item in lista) {
            if ( item!!.getUsuario()!!.getusuario() == usuario ) {
                res!!.add(item)
            }
        }
        return res
    }


    companion object {
            private var instance: ListaMisReservas? = null
            var instanceListaMisReservas: ListaMisReservas? = null
                get() {
                    if (instance != null) {
                        return instance
                    }
                    instance = ListaMisReservas()
                    return instance
                }
        }

        init {
            lista = ArrayList()
        }
    }
