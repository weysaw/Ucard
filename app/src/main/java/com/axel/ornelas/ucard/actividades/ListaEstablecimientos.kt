package com.axel.ornelas.ucard.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.axel.ornelas.ucard.adaptadores.EstablecimientosAdaptador
import com.axel.ornelas.ucard.clases.Establecimiento
import com.axel.ornelas.ucard.clases.ManejoDeDatos
import com.axel.ornelas.ucard.databinding.ActivityListaEstablecimientosBinding

class ListaEstablecimientos : AppCompatActivity() {

    private lateinit var binding: ActivityListaEstablecimientosBinding
    private lateinit var establecimientos: ArrayList<Establecimiento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaEstablecimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Obtiene la categoria y la coloca en el titulo
        val categoria = intent.getStringExtra(PARAM1) ?: return finish()
        binding.tipoEstablecimiento.text = categoria
        val idCuenta = intent.getIntExtra("idCuenta", -1)

        // Obtiene los datos del establacimiento por medio del manejador de datos
        val manejadorDeDatos = ManejoDeDatos(applicationContext, resources, packageName, assets)
        establecimientos = manejadorDeDatos.obtenerEstablecimientos(categoria)
        // Crea el adaptador y coloca la acción al presionarlo
        val establecimientosAdaptador = EstablecimientosAdaptador(establecimientos)
        establecimientosAdaptador.onClickListener = View.OnClickListener { v ->
            // Obtiene la posición en el recycler view del establecimiento presionado
            val pos = binding.listaEstablecimientos.getChildAdapterPosition(v)
            val intent = Intent(this, MostrarInfoEstablecimiento::class.java)
            // Coloca el establecimiento presionado
            with(intent) {
                putExtra("establecimiento", establecimientos[pos])
                putExtra("idCuenta", idCuenta)
            }
            startActivity(intent)
        }
        // Coloca el layout y el adaptador
        binding.listaEstablecimientos.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.listaEstablecimientos.adapter = establecimientosAdaptador
    }

}