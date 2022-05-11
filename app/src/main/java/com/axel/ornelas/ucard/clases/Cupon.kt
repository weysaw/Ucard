package com.axel.ornelas.ucard.clases

import java.io.Serializable
import java.util.*

/**
 * Cupones que contiene cualquier establecimiento
 */
class Cupon(
    val id: Int,
    val idEstablecimiento: Int,
    val promocionImagen: Int,
    val descripcion: String,
) : Serializable