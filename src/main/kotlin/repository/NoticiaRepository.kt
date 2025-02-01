package org.example.repository

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import org.example.model.Noticia

class NoticiaRepository(private val coll: com.mongodb.client.MongoCollection<Noticia>) {


    fun obtenerNoticiaPorTitulo(nombre: String): List<Noticia> {
        return coll.find(Filters.regex("titulo", ".*${nombre}.*","i")).toList()
    }

    fun obtenerNoticias(): List<Noticia> {
        return coll.find().toList()
    }

    fun insertarNoticia(noticia: Noticia) {
        coll.insertOne(noticia)
    }

    fun obtenerNoticiasPorNick(nickName: String): List<Noticia> {
        return coll.find(Filters.eq("autor",nickName)).toList()
    }

    fun obtenerNoticiasPorTag(tag:String): List<Noticia> {
        return coll.find(Filters.eq("tags",tag)).toList()
    }



    fun getUltimasNoticias(): List<Noticia> {
        return coll.find().sort(Sorts.descending("fechaPublicacion")).limit(10).toList()
    }

}