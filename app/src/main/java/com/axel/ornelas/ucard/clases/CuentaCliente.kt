package com.axel.ornelas.ucard.clases

import java.io.Serializable

class CuentaCliente(
    id: Int,
    nombre: String,
    correo: String,
    contrasena: String,
    val cuponesReclamados: ArrayList<Cupon> = arrayListOf()
) : Cuenta(id, nombre, correo, contrasena), Serializable {

}