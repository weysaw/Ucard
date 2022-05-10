package com.axel.ornelas.ucard.clases

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception


class ManejoDeDatos(
    private val applicationContext: Context,
    private val resources: Resources,
    private val packageName: String,
    private val assets: AssetManager
) {
    companion object {
        const val CUENTAS = "CUENTAS"
        const val ESTABLECIMIENTOS = "ESTABLECIMIENTOS"
    }

    /**
     * Verifica que exista un archivo de cuentas
     */
    fun obtenerCuentas(): ArrayList<Cuenta> {
        val cuentas: ArrayList<Cuenta>
        return try {
            // Abre el archivo y lee los datos de las cuentas
            applicationContext.openFileInput(CUENTAS).use {
                val obInputS = ObjectInputStream(it)
                cuentas = obInputS.readObject() as ArrayList<Cuenta>
                obInputS.close()
                it.close()
            }
            // Verifica que el archivo no este vacio
            if (cuentas.isEmpty()) guardarDatosDefectoCuenta() else cuentas
        } catch (ex: Exception) {
            println("\n\nArchivo no encontrado\n\n")
            // Si no hay nada guarda datos por defecto
            guardarDatosDefectoCuenta()
        }
    }

    /**
     * Obtiene los datos para los establecimientos y los devuelve
     */
    fun obtenerEstablecimientos(): ArrayList<Establecimiento> {
        val establecimientos: ArrayList<Establecimiento>
        return try {
            // Abre el archivo y lee los datos de las cuentas
            applicationContext.openFileInput(ESTABLECIMIENTOS).use {
                val obInputS = ObjectInputStream(it)
                establecimientos = obInputS.readObject() as ArrayList<Establecimiento>
                obInputS.close()
                it.close()
            }
            // Verifica que el archivo no este vacio
            if (establecimientos.isEmpty())
                guardarDatosDefectoEstablecimiento()
            establecimientos
        } catch (ex: Exception) {
            println("\n\nArchivo no encontrado\n\n")
            // Si no hay nada guarda datos por defecto
            guardarDatosDefectoEstablecimiento()
        }
    }


    /**
     * Guarda los datos por defecto a la memoria de la aplicación
     */
    private fun guardarDatosDefectoCuenta(): ArrayList<Cuenta> {
        val cuentas = arrayListOf<Cuenta>()
        // Se crea los datos por defecto, esto se debe de cambiar con una llamada a una base de datos
        assets.open("cuentas.txt").bufferedReader().forEachLine {
            val datos = it.split("|")
            cuentas += Cuenta(datos[0].toInt(), datos[1], datos[2], datos[3])
        }
        guardarCuentas(cuentas)
        return cuentas
    }

    /**
     * Guarda los datos por defectos de los establecimientos
     */
    private fun guardarDatosDefectoEstablecimiento(): ArrayList<Establecimiento> {
        val establecimientos = arrayListOf<Establecimiento>()

        assets.open("establecimientos.txt").bufferedReader().forEachLine {
            val datos = it.split("|")
            val fotos = arrayListOf(
                obtenerImagen(datos[0]),
                obtenerImagen(datos[1])
            )
            val logo = obtenerImagen(datos[2])
            // Nombre = 3, direccion = 4, categoria = 5
            val establecimiento = Establecimiento(datos[3], datos[4], datos[5], logo, fotos)
            val cantCupones = datos[6].toInt()
            val pos = 7
            // Recorre la cantidad de cupones y coloca la descripcion de cada uno
            for (i in pos until pos + cantCupones) {
                val texto = datos[i]
                val nuevaPromocion = obtenerImagen("cupon_$texto")
                val promocion = when (texto) {
                    "cincuenta" -> "El cupon es de 50% de descuento"
                    "treinta" -> "El cupon es de 30% de descuento"
                    else -> "El cupon es de 2 x 1 en su compra"
                }
                establecimiento.generarNuevoCupon(nuevaPromocion, promocion)
            }
            establecimientos += establecimiento
        }
        guardarEstablecimientos(establecimientos)
        return establecimientos
    }

    /**
     * Guarda la cuentas modificadas en el archivo
     */
    fun guardarCuentas(cuentas: ArrayList<Cuenta>) {
        // Se abre el archivo y guarda la info de las cuentas
        applicationContext.openFileOutput(CUENTAS, Context.MODE_PRIVATE).use {
            val os = ObjectOutputStream(it)
            os.writeObject(cuentas)
            os.close()
            it.close()
        }
    }

    /**
     * Guarda los establecimientos en el almacenamiento de la app
     */
    fun guardarEstablecimientos(establecimientos: ArrayList<Establecimiento>) {
        // Se abre el archivo y guarda la info de las cuentas
        applicationContext.openFileOutput(ESTABLECIMIENTOS, Context.MODE_PRIVATE).use {
            val os = ObjectOutputStream(it)
            os.writeObject(establecimientos)
            os.close()
            it.close()
        }
    }

    /**
     * Obtiene la imagen del drawable
     */
    private fun obtenerImagen(nombreImagen: String): Int {
        return resources.getIdentifier(nombreImagen, "drawable", packageName)
    }
}