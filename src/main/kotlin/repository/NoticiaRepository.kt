package org.example.repository

import com.mongodb.client.model.Filters
import org.example.model.Noticia

class NoticiaRepository(private val coll: com.mongodb.client.MongoCollection<Noticia>) {


    fun obtenerNoticiaPorNombre(nombre: String): List<Noticia> {
        return coll.find(Filters.eq("nombre", nombre)).toList()
    }

    fun obtenerNoticias(): List<Noticia> {
        return coll.find().toList()
    }

    fun insertarNoticia(noticia: Noticia) {
        coll.insertOne(noticia)
    }

    fun obtenerNoticiasPorNick(){
        coll.find(Filters.eq("autor",""))
    }


}