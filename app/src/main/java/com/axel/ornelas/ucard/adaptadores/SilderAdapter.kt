package com.axel.ornelas.ucard.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.axel.ornelas.ucard.R
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import java.util.ArrayList

class SliderAdapter(private val fotos: ArrayList<Int>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slide_foto, parent, false)
        return SliderAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val foto: Int = fotos[position]
        // Carga la imagen
        Glide.with(viewHolder.itemView)
            .load(foto)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }

    override fun getCount(): Int {
        return fotos.size
    }

    inner class SliderAdapterViewHolder(
        item: View,
        val imageViewBackground: ImageView = item.findViewById(R.id.fotoSlider)
    ) : ViewHolder(item)
}