package com.axel.ornelas.ucard.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.axel.ornelas.ucard.R
import com.axel.ornelas.ucard.databinding.ActivityMenuPrincipalEstablecimientoBinding

class MenuPrincipalEstablecimiento : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalEstablecimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalEstablecimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nombreEstablecimiento = intent.getStringExtra("nombre")
        binding.textView3.text = nombreEstablecimiento
        binding.imageView3.setImageResource(R.drawable.logo1)
        Toast.makeText(
            applicationContext,
            "Bienvenido Establecimiento: $nombreEstablecimiento",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * Muestra la accion que hace el boton
     */
    fun mostrarAccion(v: View) {
        Toast.makeText(
            applicationContext,
            "Proceso de escanear QR",
            Toast.LENGTH_SHORT
        ).show()
    }
}