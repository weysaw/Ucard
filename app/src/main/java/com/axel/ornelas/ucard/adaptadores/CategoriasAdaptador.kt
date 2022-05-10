package com.axel.ornelas.ucard.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axel.ornelas.ucard.R
import com.axel.ornelas.ucard.clases.Categoria
import com.bumptech.glide.Glide

class CategoriasAdaptador(private val datosLocales: ArrayList<Categoria>) :
    RecyclerView.Adapter<CategoriasAdaptador.ViewHolder>() {
    //Tal vez se deba poner protected
    lateinit var onClickListener: View.OnClickListener
    private lateinit var contexto: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        contexto = parent.context
        //Indica el estilo que debe tener el recycler
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.info_simple, parent, false)
        view.setOnClickListener(onClickListener)
        //Devuelve la vista creada
        return ViewHolder(view)
    }

    //https://www.youtube.com/watch?v=eJEUb4djDgU
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Coloca la información del puntaje en los campos de texto
        val categoria = datosLocales[position]
        holder.tipo.text = categoria.tipo
        Glide.with(contexto).load(categoria.foto).centerCrop().into(holder.imagen)
    }

    /**
     * Tamaño de los datos
     */
    override fun getItemCount(): Int {
        return datosLocales.size
    }

    /**
     * Clase interna para localizar los datos que se necesitan para colocar la info
     */
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val tipo: TextView = item.findViewById(R.id.titulo)
        val imagen: ImageView = item.findViewById(R.id.imagen)
    }
}