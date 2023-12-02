package com.example.kmm_sqldelight.taskApp.datasource

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}