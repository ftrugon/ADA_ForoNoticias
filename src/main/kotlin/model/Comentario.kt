package org.example.model

import java.util.Date

data class Comentario(
    val usuario: String,
    val noticia: Noticia,
    val texto:String,
    val fechaEscritura:Date
) {
}