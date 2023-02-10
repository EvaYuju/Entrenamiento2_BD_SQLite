package com.example.entrenamiento2_bd_sqlite

class Usuario {
    var id:Int=0
    var nombre:String=""
    var edad:Int=0

    constructor(nombre: String, edad: Int) {
        this.nombre = nombre
        this.edad = edad
    }
    constructor() {

    }

}