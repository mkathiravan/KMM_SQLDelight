package com.example.kmm_sqldelight.task_database.db

import com.example.kmm_sqldelight.database.TaskQueries
import com.example.kmm_sqldelight.task_database.db.shared.newInstance
import com.example.kmm_sqldelight.task_database.db.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

public interface TaskDatabase : Transacter {
    public val taskQueries: TaskQueries

    public companion object {
        public val Schema: SqlDriver.Schema
            get() = TaskDatabase::class.schema


    public operator fun invoke(driver: SqlDriver): TaskDatabase =
        TaskDatabase::class.newInstance(driver)

}
}
