package com.axel.ornelas.ucard.clases

import java.io.Serializable
import java.util.*

/**
 * Cupones que contiene cualquier establecimiento
 */
class Cupon(
    val id: Int,
    val promocionImagen: Int,
    val descripcion: String,
    val fechaCreacion: Calendar = Calendar.getInstance(),
    val fechaVencimiento: Calendar = fechaCreacion.clone() as Calendar
): Serializable {

    init {
        // Obtiene la fecha de vencimiento
        fechaVencimiento.add(Calendar.MONTH, 1)
    }

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