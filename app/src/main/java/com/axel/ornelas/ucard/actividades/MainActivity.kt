package com.axel.ornelas.ucard.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.axel.ornelas.ucard.clases.Cuenta
import com.axel.ornelas.ucard.clases.CuentaCliente
import com.axel.ornelas.ucard.clases.ManejoDeDatos
import com.axel.ornelas.ucard.databinding.ActivityMainBinding
import java.lang.ClassCastException
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cuentas: ArrayList<Cuenta>
    private lateinit var manejadorDeDatos: ManejoDeDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manejadorDeDatos = ManejoDeDatos(applicationContext, resources, packageName, assets)
        cuentas = manejadorDeDatos.obtenerCuentas()
    }

    /**
     * Inicia sesion buscando en el archivo de cuentas si encuentra usuario y cotraseña
     */
    fun iniciarSesion(v: View) {
        /**
         * Por el momento se deja esto luego se debe cambiar para buscar el archivo
         */
        with(binding) {
            try {
                val cuenta = cuentas.first {
                    it.correo == correo.text.toString() && it.contrasena == contrasena.text.toString()
                }
                try {
                    val cuentaCliente = cuenta as CuentaCliente
                    val intentUsuario = Intent(this@MainActivity, ListaCategorias::class.java)
                    with(intentUsuario) {
                        putExtra("nombreCuenta", cuentaCliente.nombre)
                        putExtra("idCuenta", cuentaCliente.id)
                    }
                    binding.correo.text.clear()
                    binding.contrasena.text.clear()
                    startActivity(intentUsuario)
                } catch (e: ClassCastException) { // El error indica que no es una clase de cliente
                    val intentEstablecimiento =
                        Intent(this@MainActivity, MenuPrincipalEstablecimiento::class.java)
                    intentEstablecimiento.putExtra("nombre", cuenta.nombre)
                    binding.correo.text.clear()
                    binding.contrasena.text.clear()
                    startActivity(intentEstablecimiento)
                }
            } catch (e: Exception) {
                Toast.makeText(
                    applicationContext,
                    "No existe usuario con esas credenciales",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}