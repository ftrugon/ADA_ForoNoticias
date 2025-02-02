package org.example

class ConsolaManager() {

    fun mostrarTexto(message: String,nextLine:Boolean = false){
        if (nextLine){
            println(message)
        }else print(message)
    }


    fun pedirTexto(): String {
        return readln()
    }

}