package com.todo.todo.dto.request

import com.todo.todo.model.ItemStatus
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class UpdateToDoItemRequest constructor(
    @field:Enumerated(EnumType.STRING)
    val status: ItemStatus
)
