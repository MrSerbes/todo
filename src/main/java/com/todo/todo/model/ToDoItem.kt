package com.todo.todo.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class ToDoItem(
    @Id //PRIMARY KEY
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val description: String,
    val createDate: LocalDateTime,
    val deadline: LocalDateTime,
    @field:Enumerated(EnumType.STRING)
    val status: ItemStatus,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todolist_id", referencedColumnName = "id") // UserList = Owner(fk)
    val todolist: ToDoList
) {
    constructor(name: String, description: String, toDoList: ToDoList) : this(
        "",
        name,
        description,
        LocalDateTime.now(),
        LocalDateTime.now(),
        ItemStatus.ACTIVE,
        toDoList)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ToDoItem

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , description = $description , createDate = $createDate , deadline = $deadline , status = $status )"
    }
}

enum class ItemStatus {
    COMPLETED, ACTIVE, EXPIRED
}

