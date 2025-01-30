package org.example

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

object DataBase {

    private val db_name = "ada_foro_ejercicio"

    private val cluster: MongoClient by lazy {

        val dotenv: Dotenv = dotenv()

        // guardo la variable en una conexion
        val urlMongo = dotenv["URL_MONGODB"]

        // conexion al cluster
        MongoClients.create(urlMongo)

    }

    private val db = cluster.getDatabase(db_name)

    fun closeConnection() {
        cluster.close()
    }

    fun <T> getCollection(collName:String, typeClass: Class<T>): MongoCollection<T> {
        return db.getCollection(collName, typeClass)
    }

}