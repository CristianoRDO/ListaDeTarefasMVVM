package br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.data.dao

import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.data.model.Task

object TaskDao {
    private var tasks: MutableList<Task> = mutableListOf()

    fun add(task: Task)
    {
        tasks.add(task)
    }

    fun getAll() = tasks.sortedBy { it.isCompleted }

    fun getFilterCompleted() = tasks.filter { it.isCompleted }

    fun getFilterTaskNotCompleted() = tasks.filter { !it.isCompleted }

    fun get(id: Long): Task?{
        return tasks.stream()
            .filter{t -> t.id == id}
            .findFirst()
            .orElse(null)
    }
}