package com.axel.ornelas.ucard.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axel.ornelas.ucard.clases.Cupon
import com.axel.ornelas.ucard.databinding.ActivityMostrarCuponBinding
import com.google.zxing.WriterException
import android.graphics.Point
import android.os.Build
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

class MostrarCupon : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarCuponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarCuponBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cupon = intent.getSerializableExtra("cupon") as Cupon
        val width: Int
        val height: Int
        // Verifica el tamaño de la pantalla para que el qr no sea demasiado grande
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val tamano = windowManager.currentWindowMetrics.bounds
            width = tamano.width()
            height = tamano.height()
        } else {
            val display = windowManager.defaultDisplay
            val point = Point()
            display.getSize(point)
            width = point.x
            height = point.y
        }
        var dimen = if (width < height) width else height
        dimen = dimen * 3 / 4
        //Codifica el qr con la descripcion del cupon
        val qrgEncoder = QRGEncoder(cupon.descripcion, null, QRGContents.Type.TEXT, dimen)
        try {
            val bitmap = qrgEncoder.encodeAsBitmap()
            binding.QR.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            println(e.toString())
        }
        // Obtiene las restricciones de los cupones del establecimiento
        val restricciones = intent.getStringExtra("restricciones") ?: return finish()
        binding.restricciones.text = restricciones
    }
}