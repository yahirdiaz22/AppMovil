
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.Producto
import com.example.appmovil.R

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onAddToCartClick: (Producto) -> Unit  // Cambié el nombre a 'onAddToCartClick' para más claridad
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.tvNombre)
        val descripcionTextView: TextView = view.findViewById(R.id.tvDescripcion)
        val precioTextView: TextView = view.findViewById(R.id.tvPrecio)
        val imagenImageView: ImageView = view.findViewById(R.id.imgProducto)
        val btnAgregarCarrito: Button = view.findViewById(R.id.btnAgregarCarrito)  // Referencia al botón
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.productos, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombreTextView.text = producto.nombre
        holder.descripcionTextView.text = producto.descripcion
        holder.precioTextView.text = "$${producto.precio}"
        holder.imagenImageView.setImageResource(producto.imagenUrl)

        // Establecer el clic en el botón "Agregar al carrito"
        holder.btnAgregarCarrito.setOnClickListener {
            onAddToCartClick(producto)  // Llamar a la función proporcionada para agregar el producto al carrito
        }
    }

    override fun getItemCount(): Int = productos.size
}
