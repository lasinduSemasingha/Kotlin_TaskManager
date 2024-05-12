package lasindu.example.tempo.sample.repository

import androidx.lifecycle.LiveData
import lasindu.example.tempo.sample.data.TaskDao
import lasindu.example.tempo.sample.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTask(){
        taskDao.deleteAllTasks()
    }

}