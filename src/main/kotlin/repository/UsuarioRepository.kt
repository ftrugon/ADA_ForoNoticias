package org.example.repository

import com.mongodb.client.model.Filters
import org.example.model.Usuario

class UsuarioRepository(val coll: com.mongodb.client.MongoCollection<Usuario>) {


    fun selectbyNick(nick:String):Usuario?{

        val tal = coll.find(Filters.eq("nick", nick)).toList()
        return if (tal.isEmpty()){
            null
        }else{
            tal.first()
        }

    }

    fun getUsers(): List<Usuario> {
        return coll.find().toList()
    }

    fun insertarUsuario(usuario: Usuario) {
        coll.insertOne(usuario)
    }





}