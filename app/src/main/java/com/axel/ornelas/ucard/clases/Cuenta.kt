package com.axel.ornelas.ucard.clases

import java.io.Serializable

// Se debe de hacer que la contrasña no se pueda cambiar con el objeto
class Cuenta(
    val id: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val cuponesValidos: ArrayList<Cupon> = arrayListOf(),
    val cuponesUsados: ArrayList<Cupon> = arrayListOf()
): Serializable