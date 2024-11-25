package com.example.appmovil

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class FechaCorte : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fecha_corte) // Asegúrate de que este archivo XML exista

        // Crea una instancia de GestorFechaCorte con una fecha específica
        val fechaCorteLogica = GestorFechaCorte(Date()) // Fecha actual como ejemplo

        // Mostrar la fecha de corte en un TextView
        val textViewFechaCorte = findViewById<TextView>(R.id.tvFechaCorte)
        textViewFechaCorte.text = "Fecha de Corte: ${fechaCorteLogica.obtenerFechaCorte()}"

        // Mostrar días restantes en otro TextView
        val textViewDiasRestantes = findViewById<TextView>(R.id.tvDiasRestantes)
        textViewDiasRestantes.text = "Días restantes: ${fechaCorteLogica.diasRestantesHastaCorte()}"
    }
    class GestorFechaCorte(private var fechaCorte: Date) {

        // Método para obtener la fecha de corte en formato legible
        fun obtenerFechaCorte(): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return sdf.format(fechaCorte)
        }

        // Método para calcular los días restantes hasta la fecha de corte
        fun diasRestantesHastaCorte(): Long {
            val fechaActual = Date()
            val diferencia = fechaCorte.time - fechaActual.time
            return diferencia / (1000 * 60 * 60 * 24) // Convierte la diferencia en días
        }
    }
}



