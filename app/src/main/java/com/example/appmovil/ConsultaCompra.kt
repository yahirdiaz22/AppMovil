package com.example.appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmovil.R

class ConsultaCompra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consulta_compra)

        // Encuentra el botón usando su ID
        val btnContinuar = findViewById<Button>(R.id.btn_continuar)

        // Configura el listener para el botón
        btnContinuar.setOnClickListener {
            // Muestra el mensaje de éxito con un Toast
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()

            // Llama al Intent para ir al menú (o a la actividad que desees)
            // Suponiendo que tienes una actividad llamada MenuActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finaliza la actividad actual para que el usuario no pueda volver atrás con el botón de retroceso
            finish()
        }
    }
}
