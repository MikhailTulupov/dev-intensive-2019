package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

// возращает в удобном формате дату с локалью ru
fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

// возращает время отмотанное вперед или назад
fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String =
    when (val time = kotlin.math.abs(this.time - date.time)) {
        in 0 * SECOND..1 * SECOND -> "только что"
        in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in 75 * SECOND..45 * MINUTE -> "${time / MINUTE} минут назад"
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> "${time / HOUR} часов назад"
        in 22 * HOUR..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> "${time / DAY} дней назад"
        else -> "более года назад"
}

fun TimeUnits.plural(value: Int): String {

    val time = if (value % 100 > 20) value % 10 else value % 100

    return when (this) {
        TimeUnits.SECOND -> {
            when (time) {
                1 -> "$value секунду"
                in 2..4 -> "$value секунды"
                in 5..20 -> "$value секунд"
                else -> throw IllegalStateException("seconds exception")
            }
        }
        TimeUnits.MINUTE -> {
            when (time) {
                1 -> "$value минуту"
                in 2..4 -> "$value минуты"
                in 5..20 -> "$value минут"
                else -> throw IllegalStateException("minutes exception")
            }
        }
        TimeUnits.HOUR -> {
            when (time) {
                1 -> "$value час"
                in 2..4 -> "$value часа"
                in 5..20 -> "$value часов"
                else -> throw IllegalStateException("hours exception")
            }
        }
        TimeUnits.DAY -> {
            when (time) {
                1 -> "$value день"
                in 2..4 -> "$value дня"
                in 5..20 -> "$value дней"
                else -> throw IllegalStateException("days exception")

            }
        }
    }
}