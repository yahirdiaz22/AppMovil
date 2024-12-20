package com.example.appmovil

import android.os.Parcel
import android.os.Parcelable

data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagenUrl: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeDouble(precio)
        parcel.writeInt(imagenUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Producto> {
            override fun createFromParcel(parcel: Parcel): Producto {
                return Producto(parcel)
            }

            override fun newArray(size: Int): Array<Producto?> {
                return arrayOfNulls(size)
            }
        }
    }
}
