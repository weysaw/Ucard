package com.axel.ornelas.ucard.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.axel.ornelas.ucard.adaptadores.CategoriasAdaptador
import com.axel.ornelas.ucard.clases.Categoria
import com.axel.ornelas.ucard.databinding.ActivityListaCategoriasBinding

const val PARAM1 = "categoria"

class ListaCategorias : AppCompatActivity() {

    private lateinit var binding: ActivityListaCategoriasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idCuenta = intent.getIntExtra("idCuenta", -1)
        if (idCuenta == -1)
            finish()
        // Obtiene las categorias y las fotos y aquí se puede tener un archivo o una base de datos que recopile las categorias
        val categorias = arrayListOf(
            Categoria("Consultorios", obtenerImagen("categoria_consultorios")),
            Categoria("Estéticas", obtenerImagen("categoria_esteticas")),
            Categoria("Restaurantes", obtenerImagen("categoria_restaurantes")),
            Categoria("Diseño", obtenerImagen("categoria_diseno")),
        )
        // Adaptador de categorias
        val categoriasAdaptador = CategoriasAdaptador(categorias)

        binding.listaCategoria.layoutManager = GridLayoutManager(applicationContext, 2)
        // Inicia la actividad dependiendo del restaurante
        categoriasAdaptador.onClickListener = View.OnClickListener { v ->
            val pos = binding.listaCategoria.getChildAdapterPosition(v)
            val categoria = categorias[pos].tipo
            val intent = Intent(this, ListaEstablecimientos::class.java)
            intent.putExtra(PARAM1, categoria)
            startActivity(intent)
        }
        binding.listaCategoria.adapter = categoriasAdaptador
        val cuentaNombre = intent.getStringExtra("nombreCuenta")
        Toast.makeText(
            applicationContext,
            "Bienvenido a tu cuenta: $cuentaNombre",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * Obtiene la imagen del drawable
     */
    fun obtenerImagen(imageName: String?): Int {
        return resources.getIdentifier(imageName, "drawable", packageName)
    }
}