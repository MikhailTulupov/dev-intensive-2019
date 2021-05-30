package ru.skillbranch.devintensive.models

import java.util.*

// DTO
data class User( // primary constructor
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    //Pattern Factory
    /*
    Этот шаблон позволяет решить проблему создания различных
    объектов в зависимости от некоторых условий.
    И проще вызвать метод фабрики с конкретным параметором, нежели конструктор
     */
    companion object Factory {
        private var lastId = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val parts = fullName?.split(" ")
            val firstName = parts?.get(0)
            val lastName = parts?.get(1)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}