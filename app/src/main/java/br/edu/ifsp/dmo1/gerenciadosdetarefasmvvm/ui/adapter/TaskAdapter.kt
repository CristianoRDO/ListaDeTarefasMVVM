package br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.ui.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.R
import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.data.model.Task
import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.databinding.TasklistItemBinding
import br.edu.ifsp.dmo1.gerenciadosdetarefasmvvm.ui.listener.TaskClickListener

class TaskAdapter(
    context: Context,
    private var myTasks: List<Task>,
    private val clickListener: TaskClickListener
) : ArrayAdapter<Task>(context, R.layout.tasklist_item, myTasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: TasklistItemBinding
        if (convertView == null) {
            binding = TasklistItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
            binding.root.tag = binding
        } else {
            binding = convertView.tag as TasklistItemBinding
        }

        val task = getItem(position)

        if (task != null) {
            binding.textTaskDescription.text = task.description
            if (task.isCompleted) {
                binding.imageDone.setColorFilter(ContextCompat.getColor(context, R.color.green))
            } else {
                binding.imageDone.setColorFilter(ContextCompat.getColor(context, R.color.red))
            }

            binding.imageDone.setOnClickListener{clickListener.clickDone(position)}
        }

        return binding.root
    }

    fun updateTasks(newTasks: List<Task>) {
        myTasks = newTasks
        clear()                 // Limpa o datasource do ArrayAdapter
        addAll(myTasks)           // Adiciona todas as tarefas de tasks no datasource de ArrayAdapter
        notifyDataSetChanged()  // Notifica o ArrayAdapter que houve mudança nos dados
    }
}