package lasindu.example.tempo.sample

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tempo.model.TaskListModel

class TaskDatabase(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object{
        private const val DATABASE_NAME = "tasks"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "taskList"
        private const val ID = "id"
        private const val TASK_NAME = "taskName"
        private const val TASK_DESCRIPTION = "taskDescription"
        private const val TASK_PRIORITY = "taskPriority"
        private const val TASK_DEADLINE = "taskDeadline"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$TASK_NAME TEXT, " +
                "$TASK_DESCRIPTION TEXT, " +
                "$TASK_PRIORITY TEXT, " +
                "$TASK_DEADLINE TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    @SuppressLint("Recycle", "Range")
    fun getAllTask() : List<TaskListModel> {
        val taskList = ArrayList<TaskListModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val tasks = TaskListModel()
                tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                tasks.name = cursor.getString(cursor.getColumnIndex(TASK_NAME))
                tasks.description = cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION))
                tasks.priority = cursor.getString(cursor.getColumnIndex(TASK_PRIORITY))
                tasks.deadline = cursor.getString(cursor.getColumnIndex(TASK_DEADLINE))

                taskList.add(tasks)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return taskList
    }
    //insert
    fun addTask(tasks : TaskListModel): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME, tasks.name)
        values.put(TASK_DESCRIPTION, tasks.description)
        values.put(TASK_PRIORITY, tasks.priority)
        values.put(TASK_DEADLINE, tasks.deadline)
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }
    //select the data of Particular id
    @SuppressLint("Recycle", "Range")
    fun getTask(id : Int) : TaskListModel {
        val tasks = TaskListModel()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        tasks.name = cursor.getString(cursor.getColumnIndex(TASK_NAME))
        tasks.description = cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION))
        tasks.priority = cursor.getString(cursor.getColumnIndex(TASK_PRIORITY))
        tasks.deadline = cursor.getString(cursor.getColumnIndex(TASK_DEADLINE))
        cursor.close()
        return tasks
    }
    //Delete Task
    fun deleteTask(id : Int): Boolean{
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, ID + "=?", arrayOf(id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    //Update Task
    fun updateTask(tasks: TaskListModel) : Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME, tasks.name)
        values.put(TASK_DESCRIPTION, tasks.description)
        values.put(TASK_PRIORITY, tasks.priority)
        values.put(TASK_DEADLINE, tasks.deadline)
        val success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(tasks.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}