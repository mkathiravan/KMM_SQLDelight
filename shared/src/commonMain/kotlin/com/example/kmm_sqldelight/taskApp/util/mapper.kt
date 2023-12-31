package com.example.kmm_sqldelight.taskApp.util

import com.example.kmm_sqldelight.database.Task
import com.example.kmm_sqldelight.databaseimpl.model.Task2

fun Task2.toTask(): Task {
    return Task(id, title, isDone, color, date, isImportant)
}

fun Task.toTask2(): Task2 {
    return Task2(id, title, isDone, color, date, isImportant)
}