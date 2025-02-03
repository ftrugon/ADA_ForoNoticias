package org.example.service

import org.example.ConsolaManager
import org.example.Logs
import org.example.model.Direccion
import org.example.model.Usuario
import org.example.repository.UsuarioRepository

class UsuarioService(private val usuarioRepository: UsuarioRepository, private val consola: ConsolaManager) {

    fun selectByNick(nick:String):Usuario?{
        return usuarioRepository.selectbyNick(nick)
    }

    private fun pedirDir():Direccion{
        consola.mostrarTexto("Se va a itroducir la informacion de tu direccion, si no quieres introducir esta informacion sensible introduce una cadena vacia: ",true)
        consola.mostrarTexto("Introduce tu ciudad: ",true)
        val ciudad = consola.pedirTexto()

        consola.mostrarTexto("Introduce tu codigo postal: ",true)
        val cp = consola.pedirTexto()

        consola.mostrarTexto("Introduce tu calle: ",true)
        val calle = consola.pedirTexto()

        consola.mostrarTexto("Introduce tu num: ",true)
        val num = consola.pedirTexto()

        return Direccion(ciudad,cp,calle,num)
    }

    fun insertarUsuario() {
        val allUsers = usuarioRepository.getUsers()

        consola.mostrarTexto("Introduce tu email para registrarte: ",true)
        val email = consola.pedirTexto()

        if (allUsers.any { it._id == email }) {
            Logs.escribir(listOf("ERROR","Insertar usuario","El email a insertar ya existe"))
            throw Exception("El email a insertar ya existe")
        }

        consola.mostrarTexto("Introduce tu nick para registrarte: ",true)
        val nick = consola.pedirTexto()

        if (allUsers.any { it.nick == nick }) {
            Logs.escribir(listOf("ERROR","Insertar usuario","El nick a insertar ya existe, debe ser unico"))
            throw Exception("El nick a insertar ya existe, debe ser unico")
        }

        consola.mostrarTexto("Introduce tu nombre: ",true)
        val nombre = consola.pedirTexto()

        val dir = pedirDir()

        val nums = mutableListOf<String>()

        var bandera = true

        do {
            consola.mostrarTexto("Escribe tus numeros de telefono (para parar inserta una cadena vacia): ")
            val tagToAdd = consola.pedirTexto()

            if (tagToAdd.isBlank()){
                bandera = false
            }else nums.add(tagToAdd)

        }while(bandera)

        val user = Usuario(email,nombre,nick,false,true,dir,nums)

        usuarioRepository.insertarUsuario(user)
        Logs.escribir(listOf("","Insertar usuario",user.toString()))

    }




}