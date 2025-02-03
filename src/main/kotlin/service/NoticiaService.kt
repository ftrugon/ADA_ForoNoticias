package org.example.service

import org.example.ConsolaManager
import org.example.Logs
import org.example.model.Noticia
import org.example.repository.NoticiaRepository
import java.util.*

class NoticiaService(private val noticiaRepository: NoticiaRepository, private val consola: ConsolaManager) {


    fun insertarNoticia(autor:String){

        //comprobar si el usuario existe va un nivel superior

        consola.mostrarTexto("Escribe el titulo de la noticia",true)
        val titulo = consola.pedirTexto()

        consola.mostrarTexto("Escribe el cuerpo de la noticia",true)
        val cuerpo = consola.pedirTexto()

        val fecha = Date()

        val tags = mutableListOf<String>()

        var bandera = true

        do {

            consola.mostrarTexto("Escribe tags de la noticia(para parar pon una cadena vacia): ")
            val tagToAdd = consola.pedirTexto()


            if (tagToAdd.isBlank()){
                bandera = false
            }else tags.add(tagToAdd)

        }while(bandera)


        val noticia = Noticia(titulo,cuerpo,fecha,autor,tags)

        noticiaRepository.insertarNoticia(noticia)
        Logs.escribir(listOf("","Insertar noticia",noticia.toString()))

    }

    fun getNoticiaPorNombre(noticiaName: String): List<Noticia> {
        return noticiaRepository.obtenerNoticiaPorTitulo(noticiaName)
    }

    fun getNoticiasPorNick(nickName: String): List<Noticia> {
        return noticiaRepository.obtenerNoticiasPorNick(nickName)
    }

    fun getNoticiasPorTag(tagName: String): List<Noticia> {
        return noticiaRepository.obtenerNoticiasPorTag(tagName)
    }

    fun getUltimasNoticias(): List<Noticia> {
        return noticiaRepository.getUltimasNoticias()
    }

}