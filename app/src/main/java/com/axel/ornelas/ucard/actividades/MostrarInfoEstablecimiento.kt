package com.axel.ornelas.ucard.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.axel.ornelas.ucard.adaptadores.CuponesAdaptador
import com.axel.ornelas.ucard.clases.Establecimiento
import com.axel.ornelas.ucard.databinding.ActivityMostrarInfoEstablecimientoBinding
import com.bumptech.glide.Glide

class MostrarInfoEstablecimiento : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarInfoEstablecimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarInfoEstablecimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val establecimiento = intent.getSerializableExtra("establecimiento") as Establecimiento
        binding.nombreEstablecimiento.text = establecimiento.nombre
        Glide.with(applicationContext)
            .load(establecimiento.logo)
            .fitCenter()
            .into(binding.logoEstablecimiento)
        val cuponesAdaptador = CuponesAdaptador(establecimiento.cupones)
        binding.listaCupones.layoutManager = LinearLayoutManager(applicationContext)
        binding.listaCupones.adapter = cuponesAdaptador
    }
}