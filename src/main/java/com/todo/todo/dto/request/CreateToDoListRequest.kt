package com.todo.todo.dto.request

import javax.validation.constraints.NotBlank

data class CreateToDoListRequest(
    @field:NotBlank(message = "Todo List name must not be empty or null")
    val name: String
)
