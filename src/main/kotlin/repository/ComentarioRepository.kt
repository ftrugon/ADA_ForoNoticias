package org.example.repository

import com.mongodb.client.model.Filters
import org.example.model.Comentario
import org.example.model.Noticia

class ComentarioRepository(val coll: com.mongodb.client.MongoCollection<Comentario>) {

    fun obtenerComentarios(noticia:String): List<Comentario> {
        return coll.find().toList()
    }

    fun insertarComentario(comentario: Comentario) {
        coll.insertOne(comentario)
    }

    fun obtenerComentariosPorNoticia(noticia: Noticia): List<Comentario> {
        return coll.find(Filters.regex("noticia.titulo", noticia.titulo)).toList()
    }


}