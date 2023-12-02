package com.example.kmm_sqldelight.databaseimpl.dao

import com.example.kmm_sqldelight.databaseimpl.model.Task2
import com.example.kmm_sqldelight.taskApp.util.toTask2
import com.example.kmm_sqldelight.task_database.db.TaskDatabase

fun TaskDatabase.setTask(task: Task2) {
    return taskQueries.insertTask(
        task.id,
        task.title,
        task.isDone,
        task.color,
        task.date,
        task.isImportant
    )
}

fun TaskDatabase.deleteTask(id: Long) {
    return taskQueries.deleteTask(id)
}

fun TaskDatabase.getTaskList(): List<Task2> {
    return this.taskQueries.getTasks().executeAsList().map {
        it.toTask2()
    }
}

fun TaskDatabase.updateTask(task: Task2) {
    return taskQueries.updateTask(
        task.title,
        task.isDone,
        task.color,
        task.date,
        task.isImportant,
        task.id
    )
}