package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

// возращает в удобном формате дату с локалью ru
fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}