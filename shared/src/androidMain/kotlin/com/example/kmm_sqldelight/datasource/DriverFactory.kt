package com.example.kmm_sqldelight.datasource

import android.content.Context
import com.example.kmm_sqldelight.task_database.db.TaskDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(TaskDatabase.Schema,context,"task_database.db")
    }
}