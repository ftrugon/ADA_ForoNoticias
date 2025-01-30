package org.example.model

data class Usuario(
    val _id:String,
    val nombre: String,
    val nick: String,
    val baneado:Boolean,
    val activo: Boolean,
    val direccion: Direccion,
    val telefonos: List<String>
) {
}