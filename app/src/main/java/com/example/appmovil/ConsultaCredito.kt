package com.example.appmovil

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConsultaCredito : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consulta_credito)

        // Encuentra los elementos del layout
        val btnConsultarCredito = findViewById<Button>(R.id.btnConsultarCredito)
        val llDetallesCredito = findViewById<LinearLayout>(R.id.llDetallesCredito)
        val tvCreditoInicial = findViewById<TextView>(R.id.tvCreditoInicial)
        val tvSaldoPendiente = findViewById<TextView>(R.id.tvSaldoPendiente)
        val tvFechaVencimiento = findViewById<TextView>(R.id.tvFechaVencimiento)
        val tvMontoTotal = findViewById<TextView>(R.id.tvMontoTotal)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        // Configurar el clic en el botón de consultar crédito
        btnConsultarCredito.setOnClickListener {
            // Mostrar los detalles de crédito
            llDetallesCredito.visibility = View.VISIBLE

            // Aquí puedes actualizar los TextViews con los datos reales si es necesario
            tvCreditoInicial.text = "Crédito Inicial: $3000.00"
            tvSaldoPendiente.text = "Saldo Pendiente: $1500.00"
            tvFechaVencimiento.text = "Fecha de Vencimiento: 2024-12-31"
            tvMontoTotal.text = "Monto Total: $4500.00"
        }

        // Configurar el clic en el botón de regresar
        btnRegresar.setOnClickListener {
            // Terminar la actividad y regresar a la anterior
            finish()
        }
    }
}
