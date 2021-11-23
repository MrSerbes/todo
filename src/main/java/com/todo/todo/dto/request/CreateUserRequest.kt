package com.todo.todo.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateUserRequest(

    @field:NotBlank(message = "the name value must not be empty or null")
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String

)
