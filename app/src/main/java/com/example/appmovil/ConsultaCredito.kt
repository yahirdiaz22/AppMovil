package com.example.appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConsultaCredito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consulta_credito)

        // Encuentra el botón usando su ID
        val btnContinuar = findViewById<Button>(R.id.btnRegresar)

        // Obtén los datos de la compra desde el Intent
        val producto = intent.getStringExtra("PRODUCTO")
        val monto = intent.getStringExtra("MONTO")
        val fecha = intent.getStringExtra("FECHA")

        // Aquí podrías mostrar los datos en tu interfaz si es necesario
        Toast.makeText(this, "Producto: $producto\nMonto: $monto\nFecha: $fecha", Toast.LENGTH_LONG).show()

        // Configura el listener para el botón
        btnContinuar.setOnClickListener {
            // Muestra el mensaje de éxito con un Toast
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()

            // Redirige al usuario a la actividad principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finaliza la actividad actual para evitar volver con el botón atrás
            finish()
        }
    }
}
