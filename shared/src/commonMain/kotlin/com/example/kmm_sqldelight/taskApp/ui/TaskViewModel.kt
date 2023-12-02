package com.example.kmm_sqldelight.taskApp.ui

import com.example.kmm_sqldelight.databaseimpl.dao.deleteTask
import com.example.kmm_sqldelight.databaseimpl.dao.getTaskList
import com.example.kmm_sqldelight.databaseimpl.dao.setTask
import com.example.kmm_sqldelight.databaseimpl.dao.updateTask
import com.example.kmm_sqldelight.databaseimpl.model.Task2
import com.example.kmm_sqldelight.task_database.db.TaskDatabase
import com.squareup.sqldelight.db.SqlDriver
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class TaskViewModel(driver: SqlDriver) : ViewModel() {

    val tasks = MutableStateFlow<List<Task2>>(emptyList())

    private val database = TaskDatabase(driver)

    fun addTask(task: Task2) {
        viewModelScope.launch(Dispatchers.IO) {
            database.setTask(task)
            tasks.value = database.getTaskList()
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch(Dispatchers.IO)
        {
            database.deleteTask(id)
            tasks.value = database.getTaskList()
        }
    }

    private fun getTasks() {
        viewModelScope.launch(Dispatchers.IO)
        {
            tasks.value = database.getTaskList()
                .sortedByDescending { it.date }
                .sortedByDescending { it.isImportant }
        }
    }

    fun update(task2: Task2) {
        viewModelScope.launch(Dispatchers.IO) {
            database.updateTask(task2.copy(date = Clock.System.now().toEpochMilliseconds()))
            getTasks()
        }
    }
}