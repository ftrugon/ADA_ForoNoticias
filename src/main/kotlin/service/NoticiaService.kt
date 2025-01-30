package org.example.service

import org.example.consolaManager
import org.example.model.Noticia
import org.example.repository.NoticiaRepository
import java.util.*

class NoticiaService(private val noticiaRepository: NoticiaRepository, private val consola: consolaManager) {


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
    }

    fun getNoticiaPorNombre(noticiaName: String): List<Noticia> {
        return noticiaRepository.obtenerNoticiaPorNombre(noticiaName)
    }

}