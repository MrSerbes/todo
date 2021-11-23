package com.todo.todo.dto.request

import javax.validation.constraints.NotBlank

data class CreateToDoItemRequest(
    @field:NotBlank(message = "ToDo Item name value must not be empty or null")
    val name: String,
    @field:NotBlank(message = "ToDo Item description must not be empty or null")
    val description: String
)
