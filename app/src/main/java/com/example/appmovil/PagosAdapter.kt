package com.example.tuapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.R

class PagosAdapter(private val listaPagos: List<Pago>) : RecyclerView.Adapter<PagosAdapter.PagoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pago, parent, false)
        return PagoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagoViewHolder, position: Int) {
        val pago = listaPagos[position]
        holder.descripcion.text = pago.descripcion
        holder.fecha.text = pago.fecha
        holder.monto.text = pago.monto
    }

    override fun getItemCount(): Int = listaPagos.size

    class PagoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val fecha: TextView = itemView.findViewById(R.id.tvFecha)
        val monto: TextView = itemView.findViewById(R.id.tvMonto)
    }

    data class Pago(
        val descripcion: String,
        val fecha: String,
        val monto: String
    )

}
