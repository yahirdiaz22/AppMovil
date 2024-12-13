package com.example.appmovil

import ProductoAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val productos = listOf(
        Producto(
            nombre = "Camiseta",
            descripcion = "Camisa de color Azul.",
            precio = 150.0,
            imagenUrl = R.drawable.camisa
        ),
        Producto(
            nombre = "Reloj Cassio",
            descripcion = "Rolex marca Cassio 25k.",
            precio = 2000.0,
            imagenUrl = R.drawable.cassio
        ),
        Producto(
            nombre = "Televisor LED",
            descripcion = "Televisor LED de 55 pulgadas con resolución 4K UHD.",
            precio = 899.0,
            imagenUrl = R.drawable.televisor
        ),
        Producto(
            nombre = "Laptop Gaming",
            descripcion = "Laptop gaming de 15 pulgadas, procesador Intel i7.",
            precio = 1299.0,
            imagenUrl = R.drawable.laptop
        ),
        Producto(
            nombre = "Reloj Inteligente",
            descripcion = "Reloj inteligente con monitoreo de salud.",
            precio = 1200.0,
            imagenUrl = R.drawable.reloj
        ),
        Producto(
            nombre = "Zapatos Deportivos",
            descripcion = "Zapatos deportivos cómodos.",
            precio = 775.0,
            imagenUrl = R.drawable.zapatos
        )
    )

    // Lista mutable para el carrito
    private val carrito = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Configurar el FloatingActionButton para abrir el carrito
        binding.appBarMain.fab.setOnClickListener {
            // Verificar el total del carrito
            val totalCarrito = carrito.sumByDouble { it.precio }
            if (totalCarrito > 3000) {
                Toast.makeText(this, "¡Se excedió de su crédito de $3000!", Toast.LENGTH_LONG).show()
            } else {
                // Abrir el carrito (actualiza el carrito y verifica si excede el límite)
                val intent = Intent(this, CarritoActivity::class.java)
                intent.putParcelableArrayListExtra("CARRITO", ArrayList(carrito))  // Pasa la lista del carrito
                startActivity(intent)
            }
        }

        // Configurar Navigation Drawer
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configurar RecyclerView
        setupRecyclerView()

        // Manejar navegación del Drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_cerrar_sesion -> {
                    val intent = Intent(this, InicioSesion::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_consulta_compra -> {
                    val intent = Intent(this, ConsultaCompra::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_solicitar_aumento_credito -> {
                    val intent = Intent(this, SolicitarAumentoCredito::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_consulta_credito -> {
                    val intent = Intent(this, ConsultaCredito::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_proximo_pago -> {
                    val intent = Intent(this, ProximoPago::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_fecha_corte -> {
                    val intent = Intent(this, FechaCorte::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_pagos_vencidos -> {
                    val intent = Intent(this, PagosVencidos::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_productos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductoAdapter(productos) { producto ->
            // Verificar si el producto se puede agregar al carrito
            val totalCarrito = carrito.sumByDouble { it.precio }
            if (totalCarrito + producto.precio > 3000) {
                Toast.makeText(this, "¡No se puede agregar más, se excede el crédito de $3000!", Toast.LENGTH_LONG).show()
            } else {
                carrito.add(producto)  // Agregar el producto al carrito
                Snackbar.make(recyclerView, "Añadido al carrito: ${producto.nombre}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
