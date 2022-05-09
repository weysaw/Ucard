package com.axel.ornelas.ucard.clases

// Se debe de hacer que la contrasña no se pueda cambiar con el objeto
class Cuenta(
    val correo: String,
    val contraseña: String,
    val cuponesValidos: ArrayList<Cupon>,
    val cuponesUsados: ArrayList<Cupon>
) {

}