package org.example

import org.example.model.Noticia
import org.example.model.Usuario
import org.example.service.ComentarioService
import org.example.service.NoticiaService
import org.example.service.UsuarioService

class Program(
    private val us:UsuarioService,
    private val cs:ComentarioService,
    private val ns:NoticiaService,
    private val console:consolaManager
) {


    private fun showOptions(){
        console.mostrarTexto("",true)
        console.mostrarTexto("Opciones:",true)
        console.mostrarTexto("1. Publicar una noticia:",true)
        console.mostrarTexto("2. Registrar usuario:",true)
        console.mostrarTexto("3. Escribir un comentario:",true)
        console.mostrarTexto("4. Listar noticias publicadas por un usuario:",true)
        console.mostrarTexto("5. Listar comentarios de una noticia:",true)
        console.mostrarTexto("6. Buscar noticias por etiquetas:",true)
        console.mostrarTexto("7. Mostrar las ultimas 10 noticias publicadas:",true)
        console.mostrarTexto("8. Salir del programa:",true)
        console.mostrarTexto("Elige: ")
    }

    private fun comprobarExistenciaUsuario(): Usuario? {
        console.mostrarTexto("Introduce el nick del usuario que va a publicar la noticia:",false)
        val nickPublicador = console.pedirTexto()
        return us.selectByNick(nickPublicador)

    }

    private fun insertarNoticia(){

        val existe = comprobarExistenciaUsuario()

        if (existe == null){
            throw Exception("El usuario que intenta crear la noticia no existe")
        } else if (existe.baneado || !existe.activo) {
            throw Exception("El usuario que intenta crear la noticia esta baneado o no esta activo")
        }else{
            ns.insertarNoticia(existe.nick)
        }
    }

    private fun elegirNoticia():Noticia{
        console.mostrarTexto("Dime el titulo de la noticia:")
        val nombreNoticia = console.pedirTexto()
        val noticiasConElNombre = ns.getNoticiaPorNombre(nombreNoticia)


        if (noticiasConElNombre.isEmpty()){
            throw Exception("No se encontraron noticias con ese nombre")
        }else{
            console.mostrarTexto("Elige una noticia: ",true)
            noticiasConElNombre.forEachIndexed { i, noticia ->
                console.mostrarTexto("${i + 1} --> ${noticia.titulo} : ${noticia.autor} : ${noticia.fechaPublicacion}",true)
            }
        }

        var opcion = 0

        do {
            console.mostrarTexto("Escribe el numero de la noticia que quieres: ")
            opcion = console.pedirTexto().toIntOrNull() ?: 0

        }while (opcion !in 1..noticiasConElNombre.size)
        return noticiasConElNombre[opcion - 1]
    }

    private fun insertarComentario(){
        //comprueba la existencia del usuario y pega unos errores si no existe o esta banned

        val existeUsuario = comprobarExistenciaUsuario()
        if (existeUsuario == null){
            throw Exception("El usuario que intenta crear la noticia no existe")
        } else if (existeUsuario.baneado || !existeUsuario.activo) {
            throw Exception("El usuario que intenta crear la noticia esta baneado o no esta activo")
        }

        cs.insertarComentario(existeUsuario.nick,elegirNoticia())
    }

    private fun verNoticiasUsuario(){

        val autor = comprobarExistenciaUsuario()
        if (autor == null){
            throw Exception("El usuario que intenta crear la noticia no existe")
        } else if (autor.baneado || !autor.activo) {
            throw Exception("El usuario que intenta crear la noticia esta baneado o no esta activo")
        }

        val noticias = ns.getNoticiasPorNick(autor.nick)

        if (noticias.isEmpty()){
            console.mostrarTexto("El usuario no tiene noticias")
        }else{
            noticias.forEach {
                println(it)
            }
        }
    }

    fun startProgram(){
        var op = 0
        do{
            showOptions()
            op = console.pedirTexto().toIntOrNull() ?: 0

            try {
                when(op){
                    1->{
                        insertarNoticia()
                    }
                    2->{
                        us.insertarUsuario()
                    }
                    3->{
                        insertarComentario()
                    }
                    4->{
                        verNoticiasUsuario()
                    }
                    5->{

                        val comentarios = cs.getComentariosPorNoticia(elegirNoticia())
                        comentarios.forEachIndexed { i, noticia ->
                            println("${i + 1} -> ${noticia.usuario}: ${noticia.texto}")
                        }
                    }
                    6->{
                        console.mostrarTexto("Dime el tag de la noticia que quieres buscar: ")
                        val noticias = ns.getNoticiasPorTag(console.pedirTexto())

                        if (noticias.isEmpty()){
                            console.mostrarTexto("No se ha encontrado ninfuna noticia con ese tag")
                        }else{
                            noticias.forEach {
                                println(it)
                            }
                        }

                    }
                    7->{
                        val noticias = ns.getUltimasNoticias()

                        if (noticias.isEmpty()){
                            console.mostrarTexto("No se han encotrado noticias")
                        }else{
                            noticias.forEach {
                                println(it)
                            }
                        }
                    }
                    8->{
                        console.mostrarTexto("Saliendo del programa")
                    }
                    else ->{
                        console.mostrarTexto("La opción introducida no es valida",true)
                    }
                }
            }catch (e:Exception){
                console.mostrarTexto("**ERROR**:${e.message}")
            }



        }while (op != 8)
    }

}