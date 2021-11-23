package com.todo.todo.dto

import com.todo.todo.model.ItemStatus
import java.time.LocalDateTime

data class ToDoItemDto constructor(
    val id: String,
    val name: String,
    val description: String,
    val createDate: LocalDateTime,
    val deadline: LocalDateTime,
    val status: ItemStatus
    )
