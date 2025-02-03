package org.example

import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Logs {

    private val format = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss")

    private val file = File("src/main/resources/log.txt")

    init {
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: Exception) {
            println("Error al crear el archivo de log: ${e.message}")
        }
    }

    fun escribir(accion: List<String>) {
        val tiempo = LocalDateTime.now().format(format)
        val logEntry = "${accion[0]} ${accion[1]} - $tiempo - ${accion[2]}\n"

        try {
            FileWriter(file, true).use { writer ->
                writer.append(logEntry)
            }
        } catch (e: Exception) {
            println("Error al escribir en el log: ${e.message}")
        }
    }
}