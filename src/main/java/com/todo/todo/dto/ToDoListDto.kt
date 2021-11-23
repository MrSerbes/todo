package com.todo.todo.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class ToDoListDto @JvmOverloads constructor(
    val id: String,
    val name: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val itemList: List<ToDoItemDto>? = ArrayList()
)