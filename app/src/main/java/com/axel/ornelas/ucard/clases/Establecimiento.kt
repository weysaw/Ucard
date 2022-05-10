package com.axel.ornelas.ucard.clases

import java.io.Serializable
import kotlin.collections.ArrayList

class Establecimiento(
    val nombre: String,
    val direccion: String,
    val categoria: String,
    val logo: Int,
    // Por el momento lo estoy pensando como un arreglo de direcciones pero puede cambiar
    val fotos: ArrayList<Int>,
): Serializable {
    val cupones: ArrayList<Cupon> = arrayListOf()
    private var cantCuponesCreados: Int = 0
    /**
     * Genera un nuevo cupon con el dato de la promocion
     */
    fun generarNuevoCupon(promocionImagen: Int, promocion: String) {
        cantCuponesCreados++
        val nuevoCupon = Cupon(cantCuponesCreados, promocionImagen, promocion)
        cupones += nuevoCupon
    }
}