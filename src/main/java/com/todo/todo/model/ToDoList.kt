package com.todo.todo.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class ToDoList @JvmOverloads constructor(
    @Id //PRIMARY KEY
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // UserList = Owner(fk)
    val user: User,

    @OneToMany(mappedBy = "todolist", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val toDoItems: List<ToDoItem>
)
{
    constructor(title: String, user: User) : this(
        "",
        title=title,
        user=user,
        toDoItems=listOf()
    )
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ToDoList

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , title = $title )"
    }

}
