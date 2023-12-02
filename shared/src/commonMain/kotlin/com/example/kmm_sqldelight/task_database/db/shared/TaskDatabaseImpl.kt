package com.example.kmm_sqldelight.task_database.db.shared

import com.example.kmm_sqldelight.database.Task
import com.example.kmm_sqldelight.database.TaskQueries
import com.example.kmm_sqldelight.task_database.db.TaskDatabase
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import kotlin.reflect.KClass

internal val KClass<TaskDatabase>.schema: SqlDriver.Schema
    get() = TaskDatabaseImpl.Schema

internal fun KClass<TaskDatabase>.newInstance(driver: SqlDriver): TaskDatabase =
    TaskDatabaseImpl(driver)

private class TaskDatabaseImpl(
    sqlDriver: SqlDriver
) : TransacterImpl(sqlDriver), TaskDatabase {

    public override val taskQueries: TaskQueriesImpl = TaskQueriesImpl(this, sqlDriver)

    public object Schema : SqlDriver.Schema {
        public override val version: Int
            get() = 1

        override fun create(driver: SqlDriver): Unit {
            driver.execute(
                null, """
                | CREATE TABLE Task(
                |   id INTEGER NOT NULL PRIMARY KEY,
                |   title TEXT NOT NULL,
                |   isDone INTEGER NOT NULL DEFAULT 0,
                |   color INTEGER NOT NULL,
                |   date INTEGER NOT NULL,
                |   isImportant INTEGER NOT NULL DEFAULT 0
                |)   
                """.trimMargin(), 0
            )
        }

        override fun migrate(driver: SqlDriver, oldVersion: Int, newVersion: Int): Unit {

        }

    }

}


private class TaskQueriesImpl(
    private val database: TaskDatabaseImpl,
    private val driver: SqlDriver
) : TransacterImpl(driver), TaskQueries {

    internal val getTasks: MutableList<Query<*>> = copyOnWriteList()

    override fun <T : Any> getTasks(
        mapper: (
            id: Long,
            title: String,
            isDone: Boolean,
            color: Long,
            date: Long,
            isImportant: Boolean
        ) -> T
    ): Query<T> = Query(
        -499739446, getTasks, driver, "Task.sq", "getTasks",
        "SELECT * FROM Task"
    ) { cursor ->
        mapper(
            cursor.getLong(0)!!,
            cursor.getString(1)!!,
            cursor.getLong(2)!! == 1L,
            cursor.getLong(3)!!,
            cursor.getLong(4)!!,
            cursor.getLong(5)!! == 1L
        )
    }


    public override fun getTasks(): Query<Task> =
        getTasks { id, title, isDone, color, date,
                   isImportant ->
            Task(id, title, isDone, color, date, isImportant)
        }

    override fun insertTask(
        id: Long,
        title: String,
        isDone: Boolean,
        color: Long,
        date: Long,
        isImportant: Boolean
    ): Unit {
        driver.execute(
            -2041715120, """
            |INSERT OR REPLACE INTO Task(id, title, isDone, color, date, isImportant)
            |       VALUES(?, ?, ?, ?, ?, ?)
            |""".trimMargin(), 6
        ) {
            bindLong(1, id)
            bindString(2, title)
            bindLong(3, if (isDone) 1L else 0L)
            bindLong(4, color)
            bindLong(5, date)
            bindLong(6, if (isImportant) 1L else 0L)
        }
        notifyQueries(-2041715120, { database.taskQueries.getTasks })
    }

    public override fun deleteTask(id: Long): Unit {
        driver.execute(
            -1243291582, """
    |DELETE FROM Task
    |WHERE id = ?
    """.trimMargin(), 1
        ) {
            bindLong(1, id)
        }
        notifyQueries(-1243291582, { database.taskQueries.getTasks })
    }

    public override fun updateTask(
        title: String,
        isDone: Boolean,
        color: Long,
        date: Long,
        isImportant: Boolean,
        id: Long
    ): Unit {
        driver.execute(
            991155296, """
    |UPDATE Task
    |SET title = ?, isDone = ?, color = ?, date = ?, isImportant = ?
    |WHERE id = ?
    """.trimMargin(), 6
        ) {
            bindString(1, title)
            bindLong(2, if (isDone) 1L else 0L)
            bindLong(3, color)
            bindLong(4, date)
            bindLong(5, if (isImportant) 1L else 0L)
            bindLong(6, id)
        }
        notifyQueries(991155296, { database.taskQueries.getTasks })
    }

}