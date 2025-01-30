package org.example.model

import java.util.Date

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    val fechaPublicacion: Date,
    val autor: String,
    val tags: List<String>
) {
}