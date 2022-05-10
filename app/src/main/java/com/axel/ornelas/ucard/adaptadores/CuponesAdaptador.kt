package com.axel.ornelas.ucard.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.axel.ornelas.ucard.R
import com.axel.ornelas.ucard.actividades.MostrarCupon
import com.axel.ornelas.ucard.clases.Cupon
import com.bumptech.glide.Glide

class CuponesAdaptador(private val cupones: ArrayList<Cupon>) :
    RecyclerView.Adapter<CuponesAdaptador.ViewHolder>() {
    //Tal vez se deba poner protected
    private lateinit var contexto: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        contexto = parent.context
        //Indica el estilo que debe tener el recycler
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.reclamar_cupon, parent, false)
        //Devuelve la vista creada
        return ViewHolder(view)
    }

    //https://www.youtube.com/watch?v=eJEUb4djDgU
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Coloca la información del puntaje en los campos de texto
        Glide.with(contexto)
            .load(cupones[position].promocionImagen)
            .fitCenter()
            .into(holder.imagenCupon)
        holder.boton.setOnClickListener {
            val intent = Intent(contexto, MostrarCupon::class.java)
            intent.putExtra("cupon", cupones[position])
            contexto.startActivity(intent)
        }
    }

    /**
     * Tamaño de los datos
     */
    override fun getItemCount(): Int {
        return cupones.size
    }

    /**
     * Clase interna para localizar los datos que se necesitan para colocar la info
     */
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imagenCupon: ImageView = item.findViewById(R.id.promocion)
        val boton: Button = item.findViewById(R.id.reclamar)
    }
}