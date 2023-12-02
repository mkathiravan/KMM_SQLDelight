package com.example.kmm_sqldelight.database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter

public interface TaskQueries : Transacter {
    public fun <T : Any> getTasks(
        mapper: (
            id: Long,
            title: String,
            isDone: Boolean,
            color: Long,
            date: Long,
            isImportant: Boolean
        ) -> T
    ): Query<T>

    public fun getTasks(): Query<Task>

    public fun insertTask(
        id: Long,
        title: String,
        isDone: Boolean,
        color: Long,
        date: Long,
        isImportant: Boolean
    ): Unit

    public fun deleteTask(id: Long): Unit

    public fun updateTask(
        title: String,
        isDone: Boolean,
        color: Long,
        date: Long,
        isImportant: Boolean,
        id: Long
    ): Unit
}