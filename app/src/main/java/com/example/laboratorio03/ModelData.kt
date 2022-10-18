package com.example.laboratorio03

import com.example.laboratorio03.Logica.*
import java.util.*
import kotlin.collections.ArrayList

class ModelData {
    var vueloList: ArrayList<Vuelo?>
        private set
    var avionList: ArrayList<Avion?>
    var horarioList: ArrayList<Horario?>
    var rutaList: ArrayList<Ruta?>
    var servicioList: ArrayList<Servicio?>


    private constructor(vuelo: ArrayList<Vuelo?>) {
        this.vueloList = vuelo
    }

    private constructor() {
        rutaData()
        avionData()
        horarioData()
        vueloData()
        servicioData()
    }

    fun servicioData(){
        servicioList.add(Servicio(1, "Xtreame Clean - Heredia", "Limpieza", "El equipo de nuestra empresa son especialistas, altamente capacitados y calificados, además, cuentan con amplia experiencia. Cada integrante ofrece un servicio exhaustivo y organizado de limpieza a todos nuestros clientes en Costa Rica.", 35000,4.8, "LIMPIEZA" ))
        servicioList.add(Servicio(2, "Fontanería Ramírez - Belén", "Fontanería", "Ofrecemos servicios de Mantenimiento de sistemas de tuberias a domicilio.", 42000,4.7,"FONTANERIA"))
        servicioList.add(Servicio(3, "Carpintería Woodworks - Barva", "Carpintería", "Diseñamos y Creamos los mejores muebles, para su hogar entendiendo a nuestro cliente en lo que el quiere y necesita, Trabajos con los mejores materiales.", 62000,4.5, "CARPINTERIA" ))
        servicioList.add(Servicio(4, "Electricista Intelco - Mercedes", "Electricista", "Detección y reparación de fallas en instalaciones eléctricas", 44000,4.6,"ELECTRICISTA"))
        servicioList.add(Servicio(4, "ProClean Muebles - Santa Lucía", "Limpieza de Muebles", "Limpieza correctiva a domicilio de sillones, carros, colchones, alfombras, sillas, muebles.", 29000,4.9,"MUEBLES"))
    }

    fun vueloData(){
        vueloList.add(Vuelo(1,avionList!!.get(0),rutaList!!.get(0),horarioList!!.get(0),250))
        vueloList.add(Vuelo(2,avionList!!.get(0),rutaList!!.get(1),horarioList!!.get(1),150))
        vueloList.add(Vuelo(3,avionList!!.get(1),rutaList!!.get(2),horarioList!!.get(2),60))
        vueloList.add(Vuelo(4,avionList!!.get(2),rutaList!!.get(3),horarioList!!.get(3),130))
        vueloList.add(Vuelo(5,avionList!!.get(3),rutaList!!.get(4),horarioList!!.get(4),145))
        vueloList.add(Vuelo(6,avionList!!.get(4),rutaList!!.get(5),horarioList!!.get(5),100))
        vueloList.add(Vuelo(7,avionList!!.get(5),rutaList!!.get(6),horarioList!!.get(6),150))
        vueloList.add(Vuelo(8,avionList!!.get(6),rutaList!!.get(0),horarioList!!.get(0),50))
        vueloList.add(Vuelo(9,avionList!!.get(2),rutaList!!.get(0),horarioList!!.get(0),70))
        vueloList.add(Vuelo(10,avionList!!.get(4),rutaList!!.get(1),horarioList!!.get(1),40))
        vueloList.add(Vuelo(11,avionList!!.get(6),rutaList!!.get(1),horarioList!!.get(1),150))
        vueloList.add(Vuelo(12,avionList!!.get(3),rutaList!!.get(2),horarioList!!.get(2),200))
        vueloList.add(Vuelo(13,avionList!!.get(6),rutaList!!.get(8),horarioList!!.get(1),550))
        vueloList.add(Vuelo(14,avionList!!.get(3),rutaList!!.get(7),horarioList!!.get(2),800))

    }

    fun rutaData(){
        rutaList.add(Ruta(1,"SJO-MIA","3 h","no",0))
        rutaList.add(Ruta(2,"SJO-LA","2 h 30 min","no",0))
        rutaList.add(Ruta(3,"SJO-PAR","15 h 25 min","no",0))
        rutaList.add(Ruta(4,"SJO-SWE","19 h","no",0))
        rutaList.add(Ruta(5,"SJO-TYO","25 h","si",25))
        rutaList.add(Ruta(6,"SJO-GUA","2 h","si",10))
        rutaList.add(Ruta(7,"SJO-PAM","1h 30 min","no",0))
        rutaList.add(Ruta(6,"SJO-CAR","5 h","si",15))
        rutaList.add(Ruta(7,"SJO-CMX","3 h 40 min","no",0))


    }

    fun avionData(){
        avionList.add(Avion(1,2019,"Boeing 747","American Airlines",400,200,2))
        avionList.add(Avion(2,2017,"Airbus A320","American Airlines",400,200,2))
        avionList.add(Avion(3,2018,"Boeing 757","Aeroflot",400,200,2))
        avionList.add(Avion(4,2020,"Airbus A20","Iberia",400,200,2))
        avionList.add(Avion(5,2021,"McDonnell Douglas MD-80","American Airlines",400,200,2))
        avionList.add(Avion(6,2020,"Airbus A330","Iberia",400,200,2))
        avionList.add(Avion(7,2021,"McDonnell Douglas MD-80","American Airlines",400,200,2))

    }

    fun horarioData(){
        horarioList.add(Horario(1,"8:00 a.m","11:00 a.m"))
        horarioList.add(Horario(2,"9:00 a.m","12:00 p.m"))
        horarioList.add(Horario(3,"8:00 a.m","1:00 a.m"))
        horarioList.add(Horario(4,"6:00 p.m","6:00 a.m"))
        horarioList.add(Horario(5,"2:00 p.m","11:00 p.m"))
        horarioList.add(Horario(6,"7:30 a.m","10:00 a.m"))
        horarioList.add(Horario(7,"8:00 p.m","7:00 a.m"))

    }

    fun getJobApplication(position : Int): Vuelo? {
        return vueloList.get(position)
    }

    fun getVueloLista(): ArrayList<Vuelo?> {
        return vueloList
    }

    fun getServicioLista(): ArrayList<Servicio?> {
        return servicioList
    }

    fun buscarVuelo(ruta: String?): ArrayList<Vuelo?>? {
        var vuelos: ArrayList<Vuelo?>? = ArrayList<Vuelo?>()
        for (item in vueloList) {
            if ( item!!.getRuta()!!.getRuta() == ruta ) {
                vuelos!!.add(item)
            }
        }
        return vuelos
    }

    fun editJobApplication(position: Int,updateJobApplication: Vuelo){
        vueloList.set(position,updateJobApplication)
    }

    companion object {
        private var instance: ModelData? = null
        var instanceModelData: ModelData? = null
            get() {
                if (instance != null) {
                    return instance
                }
                instance = ModelData()
                return instance
            }
    }

    init {
        vueloList = ArrayList()
        avionList = ArrayList()
        horarioList = ArrayList()
        rutaList = ArrayList()
        servicioList = ArrayList()
    }
}
