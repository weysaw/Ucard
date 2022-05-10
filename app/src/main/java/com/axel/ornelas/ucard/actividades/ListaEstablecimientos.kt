package com.axel.ornelas.ucard.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.axel.ornelas.ucard.adaptadores.EstablecimientosAdaptador
import com.axel.ornelas.ucard.clases.Establecimiento
import com.axel.ornelas.ucard.databinding.ActivityListaEstablecimientosBinding

class ListaEstablecimientos : AppCompatActivity() {

    private lateinit var binding: ActivityListaEstablecimientosBinding
    private val establecimientos = arrayListOf<Establecimiento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaEstablecimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoria = intent.getStringExtra(PARAM1) ?: return finish()
        /**
         * De mientras coloco esto para obtener el local
         */
        if (categoria.equals("Restaurantes", true)) {
            val fotos = arrayListOf<Int>(
                obtenerImagen("establecimiento_senaii_1"),
                obtenerImagen("establecimiento_senaii_2")
            )
            val logo = obtenerImagen("establecimiento_senaii_logo")
            val establecimiento = Establecimiento(
                "Sen - Aii: Ramen House",
                "Av perrona",
                "Restaurantes",
                logo,
                fotos
            )
            val nuevaPromocion = obtenerImagen("cupon_treinta")
            establecimiento.generarNuevoCupon(nuevaPromocion, "30% de descuento")
            establecimientos.add(establecimiento)
            val establecimientosAdaptador = EstablecimientosAdaptador(establecimientos)
            establecimientosAdaptador.onClickListener = View.OnClickListener { v ->
                val pos = binding.listaEstablecimientos.getChildAdapterPosition(v)
                val intent = Intent(this, MostrarInfoEstablecimiento::class.java)
                val establecimientoMandar = establecimientos[pos]
                println(establecimientoMandar.nombre)
                intent.putExtra("establecimiento", establecimientoMandar)
                startActivity(intent)
            }
            binding.listaEstablecimientos.layoutManager = GridLayoutManager(applicationContext, 2)
            binding.listaEstablecimientos.adapter = establecimientosAdaptador
        }

    }

    /**
     * Obtiene la imagen del drawable
     */
    fun obtenerImagen(imageName: String?): Int {
        return resources.getIdentifier(imageName, "drawable", packageName)
    }
}