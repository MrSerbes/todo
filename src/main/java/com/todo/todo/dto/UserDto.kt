package com.todo.todo.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class UserDto @JvmOverloads constructor(
    val id: String,
    val name: String,
    val email: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val todos: List<ToDoListDto>? = ArrayList()
)