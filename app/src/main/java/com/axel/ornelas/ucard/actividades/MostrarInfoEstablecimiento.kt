package com.axel.ornelas.ucard.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.axel.ornelas.ucard.adaptadores.CuponesAdaptador
import com.axel.ornelas.ucard.adaptadores.SliderAdapter
import com.axel.ornelas.ucard.clases.Establecimiento
import com.axel.ornelas.ucard.clases.ManejoDeDatos
import com.axel.ornelas.ucard.databinding.ActivityMostrarInfoEstablecimientoBinding
import com.bumptech.glide.Glide

class MostrarInfoEstablecimiento : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarInfoEstablecimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarInfoEstablecimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtiene el establecimiento de la actividad pasada
        val establecimiento = intent.getSerializableExtra("establecimiento") as Establecimiento
        binding.nombreEstablecimiento.text = establecimiento.nombre
        //Carga el logo con glide
        Glide.with(applicationContext)
            .load(establecimiento.logo)
            .fitCenter()
            .into(binding.logoEstablecimiento)
        val idCuenta = intent.getIntExtra("idCuenta", -1)
        if (idCuenta == -1)
            finish()
        // Se utiliza para reclamar los cupones
        val manejoDeDatos = ManejoDeDatos(applicationContext, resources, packageName, assets)
        val cuponesAdaptador = CuponesAdaptador(establecimiento.cupones, idCuenta, manejoDeDatos)
        binding.listaCupones.layoutManager = LinearLayoutManager(applicationContext)
        binding.listaCupones.adapter = cuponesAdaptador

        val sliderAdapter = SliderAdapter(establecimiento.fotos)
        with(binding.slider) {
            setSliderAdapter(sliderAdapter)
            scrollTimeInSec = 3
            isAutoCycle = true
            startAutoCycle()
        }
    }
}