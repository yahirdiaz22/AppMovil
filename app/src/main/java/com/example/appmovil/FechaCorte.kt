package com.example.appmovil

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FechaCorte: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fecha_corte)

        val btnRegresarMenu: Button = findViewById(R.id.btnRegresarMenu)
        btnRegresarMenu.setOnClickListener {
            // Lógica para regresar al menú o cerrar la actividad
            finish()
        }
    }
}



