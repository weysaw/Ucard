package com.axel.ornelas.ucard.clases

import java.util.*
import kotlin.collections.ArrayList

class Establecimiento(
    val nombre: String,
    val direccion: String,
    // Por el momento lo estoy pensando como un arreglo de direcciones pero puede cambiar
    val fotos: ArrayList<String>,
    val cupones: ArrayList<Cupon>
) {
    private var cantCuponesCreados: Int = 0
    /**
     * Genera un nuevo cupon con el dato de la promocion
     */
    fun generarNuevoCupon(promocion: String) {
        cantCuponesCreados++
        // Obtiene la fecha actual
        val fechaActual = Calendar.getInstance()
        val fechaVencimiento = fechaActual.clone() as Calendar
        // Obtiene la fecha de vencimiento
        fechaVencimiento.add(Calendar.MONTH, 1) // Zero-based months
        val nuevoCupon = Cupon(cantCuponesCreados, nombre, promocion, fechaActual, fechaVencimiento)
        cupones += nuevoCupon
    }
}