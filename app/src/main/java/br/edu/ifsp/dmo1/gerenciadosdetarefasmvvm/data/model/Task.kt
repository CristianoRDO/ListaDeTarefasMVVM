package br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.data.model

class Task (var description: String, var isCompleted: Boolean){

    private companion object{ // Parte estática da classe (seria algo tipo private static var lastId: Long = 1L)
        var lastId: Long = 1L
    }

    var id: Long = 0L

    init{
        id = lastId
        lastId += 1
    }

}