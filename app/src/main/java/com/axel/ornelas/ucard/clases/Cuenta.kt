package com.axel.ornelas.ucard.clases

import java.io.Serializable

// Se debe de hacer que la contrasña no se pueda cambiar con el objeto
open class Cuenta(
    val id: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String,
): Serializable