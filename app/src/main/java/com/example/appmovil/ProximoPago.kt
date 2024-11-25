package com.example.appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProximoPago : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.proximo_pago)

        // Lista de productos para mostrar en el ListView
        val productos = listOf(
            "Producto: Televisor LED\nCantidad: 1\nPrecio: $1000.00",
            "Producto: Estuche de Carga\nCantidad: 1\nPrecio: $200.00"
        )

        // Obtener el ListView desde el XML
        val listView: ListView = findViewById(R.id.listViewProductos)

        // Crear un ArrayAdapter para conectar la lista de productos con el ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productos)

        // Establecer el adaptador en el ListView
        listView.adapter = adapter

        // Obtener los botones desde el XML
        val btnPagarAhora: Button = findViewById(R.id.btnPagarAhora)
        val btnCancelar: Button = findViewById(R.id.btnCancelar)

        // Funcionalidad para el botón "Pagar ahora"
        btnPagarAhora.setOnClickListener {
            // Aquí puedes agregar la lógica para procesar el pago
            // Ejemplo: mostrar un mensaje de confirmación
            Toast.makeText(this, "Pago procesado exitosamente", Toast.LENGTH_SHORT).show()

            // Si deseas redirigir a otra actividad (por ejemplo, pantalla de confirmación de pago):
            val intent = Intent(this, MainActivity::class.java) // Cambia a la actividad de confirmación de pago
            startActivity(intent)
        }

        // Funcionalidad para el botón "Cancelar"
        btnCancelar.setOnClickListener {
            // Aquí puedes agregar la lógica para cancelar la acción
            // Ejemplo: volver a la actividad anterior
            finish()  // Cierra la actividad actual y vuelve a la anterior
        }
    }
}
