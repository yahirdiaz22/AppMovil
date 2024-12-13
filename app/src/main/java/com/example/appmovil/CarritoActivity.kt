package com.example.appmovil
import ProductoAdapter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarritoActivity : AppCompatActivity() {

    private val carrito = mutableListOf<Producto>()  // Asegúrate de que sea una MutableList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.carrito)

        // Obtener los productos del carrito que se pasaron a la actividad
        val carritoRecibido = intent.getParcelableArrayListExtra<Producto>("CARRITO") ?: emptyList()
        carrito.addAll(carritoRecibido)

        // Calcular el total
        val total = carrito.sumByDouble { it.precio }  // Sumar los precios de todos los productos

        // Configurar RecyclerView para mostrar los productos en el carrito
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_carrito)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductoAdapter(carrito) { producto ->
            // Aquí solo muestra el mensaje, no es necesario agregar al carrito ya que están en el carrito
            Toast.makeText(this, "Producto en carrito: ${producto.nombre}", Toast.LENGTH_SHORT).show()
        }

        // Mostrar el total en un TextView
        val totalTextView: TextView = findViewById(R.id.tvTotal)
        totalTextView.text = "Total: $${"%.2f".format(total)}"  // Mostrar el total con 2 decimales

        // Verificar si el total excede los $3000
        val btnPagar: Button = findViewById(R.id.btnPagar)

        if (total > 3000) {
            // Si el total excede los $3000, deshabilitar el botón de pagar
            btnPagar.isEnabled = false
            btnPagar.text = "Límite de $3000 superado"
            btnPagar.setBackgroundColor(Color.GRAY)  // Cambiar color del botón para indicar que no está habilitado
        } else {
            // Si el total está por debajo del límite, habilitar el botón de pagar
            btnPagar.isEnabled = true
            btnPagar.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
        }

        // Configurar el botón de "Pagar"
        btnPagar.setOnClickListener {
            if (btnPagar.isEnabled) {
                // Realizar el pago y limpiar el carrito
                realizarPago(carrito)
                carrito.clear()  // Limpiar el carrito

                // Actualizar la vista (se puede cerrar la actividad o resetear la vista)
                Toast.makeText(this, "Pago realizado con éxito", Toast.LENGTH_SHORT).show()
                finish()  // Cierra la actividad, opcionalmente puedes reiniciar la actividad del carrito
            } else {
                // Si el carrito excede el límite, mostrar un mensaje
                Toast.makeText(this, "El carrito excede el límite de $3000", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para simular el pago
    private fun realizarPago(carrito: List<Producto>) {
        // Aquí puedes agregar la lógica para procesar el pago
        // Ejemplo: llamar a una API, guardar en una base de datos, etc.
        // Este es un simple ejemplo de log:
        Log.d("CarritoActivity", "Procesando el pago para los productos: ${carrito.map { it.nombre }}")
    }
}
