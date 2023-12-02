package com.example.kmm_sqldelight.datasource

import com.example.kmm_sqldelight.task_database.db.TaskDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TaskDatabase.Schema, "task_database.db")
    }
}