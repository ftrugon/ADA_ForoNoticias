package org.example.repository

import org.example.model.Comentario

class ComentarioRepository(val coll: com.mongodb.client.MongoCollection<Comentario>) {

    fun obtenerComentarios(noticia:String): List<Comentario> {
        return coll.find().toList()
    }

    fun insertarComentario(comentario: Comentario) {
        coll.insertOne(comentario)
    }



}