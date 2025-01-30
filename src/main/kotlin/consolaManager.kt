package org.example

class consolaManager() {

    fun mostrarTexto(message: String,nextLine:Boolean = false){
        if (nextLine){
            println(message)
        }else print(message)
    }


    fun pedirTexto(): String {
        return readln()
    }

}