package com.axel.ornelas.ucard.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.axel.ornelas.ucard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun obtenerCuentas() {


    }

    /**
     * Inicia sesion buscando en el archivo de cuentas si encuentra usuario y cotraseña
     */
    fun iniciarSesion(v: View) {
        /**
         * Por el momento se deja esto luego se debe cambiar para buscar el archivo
         */
        with(binding) {
            if (correo.text.toString() == "hola" && contrasena.text.toString() == "1234") {
                val intent = Intent(this@MainActivity, ListaEstablecimientos::class.java)
                startActivity(intent)
            }
        }

    }
}