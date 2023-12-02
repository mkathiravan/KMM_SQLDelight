package com.example.kmm_sqldelight.database

public data class Task(
    public val id: Long,
    public val title: String,
    public val isDone: Boolean,
    public val color: Long,
    public val date: Long,
    public val isImportant: Boolean
) {
    public override fun toString(): String = """"
        |Task[
        | id: $id
        | title: $title
        | isDone: $isDone
        | color: $color
        | date : $date
        | isImportant: $isImportant
        |]
    """.trimMargin()

}