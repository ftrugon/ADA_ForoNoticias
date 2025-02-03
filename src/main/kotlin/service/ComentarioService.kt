package org.example.service

import org.example.ConsolaManager
import org.example.Logs
import org.example.model.Comentario
import org.example.model.Noticia
import org.example.repository.ComentarioRepository
import java.util.Date

class ComentarioService(private val comentarioRepository: ComentarioRepository,private val consola: ConsolaManager) {

    fun insertarComentario(usuario: String,noticia: Noticia) {

        consola.mostrarTexto("Escribe el mensaje de el comentario: ")
        val texto = consola.pedirTexto()

        val comentario = Comentario(usuario,noticia,texto,Date())

        comentarioRepository.insertarComentario(comentario)
        Logs.escribir(listOf("","Insertar comentario",comentario.toString()))
    }

    fun getComentariosPorNoticia(noticia: Noticia): List<Comentario> {
        return comentarioRepository.obtenerComentariosPorNoticia(noticia)
    }

}