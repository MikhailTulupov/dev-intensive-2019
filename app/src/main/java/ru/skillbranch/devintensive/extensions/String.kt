package ru.skillbranch.devintensive.extensions

import java.util.regex.Matcher
import java.util.regex.Pattern

// метод для усечение строки по диапаззону
fun String.truncate(range: Int = 16): String {
    var str = this.substring(0,range)
    str = if(str.last() == ' ' && str[range-1] != ' ')
        str.substring(0,range-1) + "..."
    else
        str.substring(0,range) + "..."
    return str
}
// метод лля извлечение строки из html
fun String.stripHtml():String {
    val listPatterns = listOf("<[^>]*>", "\\s{2,}")
    val sb = StringBuffer()
    val matcher: Matcher = Pattern.compile(listPatterns[0]).matcher(this)

    for(i in 1..2) {
        while (matcher.find())
            if (matcher.pattern().pattern() == listPatterns[0])
                matcher.appendReplacement(sb, "")
            else
                matcher.appendReplacement(sb, " ")
        if (i == 2)
            break
        matcher.usePattern(Pattern.compile(listPatterns[1])).reset(sb.toString())
        sb.setLength(0)
    }

    return matcher.appendTail(sb).toString()
}