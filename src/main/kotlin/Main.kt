package org.example

import org.example.model.Comentario
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.repository.ComentarioRepository
import org.example.repository.NoticiaRepository
import org.example.repository.UsuarioRepository
import org.example.service.ComentarioService
import org.example.service.NoticiaService
import org.example.service.UsuarioService

fun main() {

    val consola = consolaManager()


    try {
        consola.mostrarTexto("Obteniendo la coleccion de usuarios",true)
        val usuariosColl = DataBase.getCollection("coll_usuarios",Usuario::class.java)
        val userRepository = UsuarioRepository(usuariosColl)

        consola.mostrarTexto("Obteniendo la coleccion de noticias",true)
        val noticiasColl = DataBase.getCollection("coll_noticias",Noticia::class.java)
        val noticiaRepository = NoticiaRepository(noticiasColl)


        consola.mostrarTexto("Obteniendo la coleccion de comentarios",true)
        val comentariosColl = DataBase.getCollection("coll_comentarios",Comentario::class.java)
        val comentarioRepository = ComentarioRepository(comentariosColl)

        val userServie = UsuarioService(userRepository,consola)
        val noticiaService = NoticiaService(noticiaRepository,consola)
        val comentarioService = ComentarioService(comentarioRepository,consola)

        val program = Program(userServie,comentarioService,noticiaService,consola)

        program.startProgram()

    }catch (ex: Exception){
        consola.mostrarTexto("Algo salio mal: ${ex.message}",true)
        DataBase.closeConnection()
    }

}