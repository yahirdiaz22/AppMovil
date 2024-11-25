package com.example.appmovil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SolicitarAumentoCredito : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solicitar_aumento_credito)

        val btnComprobante = findViewById<Button>(R.id.btnComprobante)
        val btnIne = findViewById<Button>(R.id.btnINE)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val comprobantePreview = findViewById<TextView>(R.id.comprobantePreview)
        val inePreview = findViewById<ImageView>(R.id.inePreview)

        btnComprobante.setOnClickListener {
            // Abre el selector de archivos para cargar el PDF
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(intent, 1)
        }

        btnIne.setOnClickListener {
            // Abre el selector de archivos para cargar una imagen
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
        }

        btnEnviar.setOnClickListener {
            // Muestra un mensaje de éxito y redirige al menú
            Toast.makeText(this, "Formulario enviado correctamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                1 -> {
                    // Comprobante de domicilio cargado (PDF)
                    val uri = data.data
                    val preview = findViewById<TextView>(R.id.comprobantePreview)
                    preview.text = "Comprobante cargado: ${uri?.lastPathSegment}"
                    preview.visibility = TextView.VISIBLE
                }
                2 -> {
                    // INE cargado (Imagen)
                    val uri = data.data
                    val preview = findViewById<ImageView>(R.id.inePreview)
                    preview.setImageURI(uri)
                    preview.visibility = ImageView.VISIBLE
                }
            }
        }
    }
}
