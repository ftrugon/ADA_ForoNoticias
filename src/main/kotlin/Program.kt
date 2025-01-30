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

    private fun insertarComentario(){
        //comprueba la existencia del usuario y pega unos errores si no existe o esta banned

        val existeUsuario = comprobarExistenciaUsuario()
        if (existeUsuario == null){
            throw Exception("El usuario que intenta crear la noticia no existe")
        } else if (existeUsuario.baneado || !existeUsuario.activo) {
            throw Exception("El usuario que intenta crear la noticia esta baneado o no esta activo")
        }

        // comprueba la noticia
        console.mostrarTexto("Dime el titulo de la noticia:")
        val nombreNoticia = console.pedirTexto()
        val noticiasConElNombre = ns.getNoticiaPorNombre(nombreNoticia)


        if (noticiasConElNombre.isEmpty()){
            throw Exception("No se encontraron noticias con ese nombre")
        }else{
            console.mostrarTexto("Elige una noticia: ")
            noticiasConElNombre.forEachIndexed { i, noticia ->
                console.mostrarTexto("${i + 1} --> ${noticia.titulo} : ${noticia.autor} : ${noticia.fechaPublicacion}")
            }
        }

        var opcion = 0

        do {
            console.mostrarTexto("Escribe el numero de la noticia que quieres: ")
            opcion = console.pedirTexto().toIntOrNull() ?: 0

        }while (opcion !in 1..noticiasConElNombre.size)

        cs.insertarComentario(existeUsuario.nick,noticiasConElNombre[opcion-1])
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

                    }
                    5->{

                    }
                    6->{

                    }
                    7->{

                    }
                    8->{

                    }
                    else ->{

                    }
                }
            }catch (e:Exception){
                console.mostrarTexto("**ERROR**:${e.message}")
            }



        }while (op != 6)
    }

}