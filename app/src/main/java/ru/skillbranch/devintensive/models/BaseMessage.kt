package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    enum class TypeMessage {
        IMAGE,
        TEXT
    }

    abstract fun formatMessage():String

    // Pattern Factory
    companion object AbstractFactory {
        var lastId = -1
        fun makeMessage(
            from: User,
            chat: Chat,
            date: Date,
            type: TypeMessage,
            payload: Any?
        ): BaseMessage {
            lastId++
            return when (type) {
                TypeMessage.IMAGE -> ImageMessage(
                    "$lastId",
                    from,
                    chat,
                    date = date,
                    image = payload as String
                )
                TypeMessage.TEXT -> TextMessage(
                    "$lastId",
                    from,
                    chat,
                    date = date,
                    text = payload as String
                )
            }
        }
    }
}