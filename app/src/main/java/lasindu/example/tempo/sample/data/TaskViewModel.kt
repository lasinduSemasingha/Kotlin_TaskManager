package lasindu.example.tempo.sample.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lasindu.example.tempo.sample.data.Task
import lasindu.example.tempo.sample.data.TaskDatabase
import lasindu.example.tempo.sample.data.TaskRepository

class TaskViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val userDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

}