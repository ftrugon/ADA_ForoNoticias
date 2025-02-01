package org.example.service

import org.example.consolaManager
import org.example.model.Comentario
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.repository.ComentarioRepository
import java.util.Date

class ComentarioService(private val comentarioRepository: ComentarioRepository,private val consola: consolaManager) {

    fun insertarComentario(usuario: String,noticia: Noticia) {


        consola.mostrarTexto("Escribe el mensaje de el comentario: ")
        val texto = consola.pedirTexto()

        val comentario = Comentario(usuario,noticia,texto,Date())

        comentarioRepository.insertarComentario(comentario)
    }

    fun getComentariosPorNoticia(noticia: Noticia): List<Comentario> {
        return comentarioRepository.obtenerComentariosPorNoticia(noticia)
    }

}