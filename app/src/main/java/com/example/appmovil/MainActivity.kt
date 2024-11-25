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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // Lista de productos
    private val productos = listOf(
        Producto("Camiseta", "Camiseta azul de algodón", 150.0),
        Producto("Reloj", "Reloj deportivo resistente al agua", 250.0),
        Producto("Audífonos", "Audífonos inalámbricos con cancelación de ruido", 800.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Configurar el FloatingActionButton
        binding.appBarMain.fab.setOnClickListener {
            Toast.makeText(this, "Carrito abierto", Toast.LENGTH_SHORT).show()
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
            Snackbar.make(recyclerView, "Añadido al carrito: ${producto.nombre}", Snackbar.LENGTH_SHORT).show()
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
