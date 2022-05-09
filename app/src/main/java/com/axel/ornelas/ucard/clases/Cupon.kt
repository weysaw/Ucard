package com.axel.ornelas.ucard.clases

import java.util.*

/**
 * Cupones que contiene cualquier establecimiento
 */
class Cupon(
    val id: Int,
    val nombreEstablecimiento: String,
    val promocion: String,
    val fechaCreacion: Calendar,
    val fechaVencimiento: Calendar,
) {
    fun verificarValidezCupon(): Boolean {
        val fechaActual = Calendar.getInstance()
        return if (fechaVencimiento <= fechaActual)
            true
        else {
            //Verificar que en el archivo que el cupon existe
            false
        }

    }
}