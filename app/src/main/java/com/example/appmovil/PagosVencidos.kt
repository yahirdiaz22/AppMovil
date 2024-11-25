package com.example.appmovil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuapp.PagosAdapter
import com.google.android.material.button.MaterialButton

class PagosVencidos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var regresarButton: MaterialButton
    private lateinit var pagosAdapter: PagosAdapter
    private val listaPagos = listOf(
        PagosAdapter.Pago("Pago 1", "2024-11-20", "$500"),
        PagosAdapter.Pago("Pago 2", "2024-11-18", "$1000"),
        PagosAdapter.Pago("Pago 3", "2024-11-15", "$750")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagos_vencidos)

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.dgPagosVencidos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        pagosAdapter = PagosAdapter(listaPagos)
        recyclerView.adapter = pagosAdapter

        // Configuración del botón "Regresar al Menú"
        regresarButton = findViewById(R.id.btnRegresarMenu)
        regresarButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
