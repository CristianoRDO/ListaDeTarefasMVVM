package br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.ui.main

import android.icu.text.Transliterator.Position
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.data.dao.TaskDao
import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.data.model.Task

class MainViewModel: ViewModel() {

    private val dao = TaskDao
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    private val _insertedTask = MutableLiveData<Boolean>()
    val insertedTask: LiveData<Boolean> = _insertedTask

    private val _updateTask = MutableLiveData<Boolean>()
    val updateTask: LiveData<Boolean>
        get() = _updateTask

    private val _filterTask = MutableLiveData<Int>()
    val filterTask: LiveData<Int> = _filterTask

    init{
        _filterTask.value = 0
        mock()
        load()
    }

    fun insertTask(description: String){
        val task = Task(description, false)
        dao.add(task)
        _insertedTask.value = true
        load()
    }

    fun updateTask(position: Int) {
        val task = dao.getAll()[position]
        task.isCompleted = !task.isCompleted
        _updateTask.value = true
        load()
    }

    private fun load() {
        _tasks.value = when(_filterTask.value){
            0 -> dao.getAll()
            1 -> dao.getFilterTaskNotCompleted()
            2 -> dao.getFilterCompleted()
            else -> dao.getAll()
        }
    }

    private fun mock() {
        dao.add(Task("Implementar Exercicio DMO", false))
        dao.add(Task("Tomar Café na Cantina", true))
        dao.add(Task("Desligar Computador ao Sair", false))
        dao.add(Task("Arrumar a Cadeira ao Sair", false))
        dao.add(Task("Arrumar as Tomadas ao Sair", false))
    }

    fun updateFilter(filter: Int){
        _filterTask.value = filter
        load()
    }

}