package ru.skillbranch.devintensive.utils

import android.content.Context
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.roundToInt

object Utils {

    private var mapSymbols: HashMap<String, String> = HashMap()
    private val a_zA_ZInterval = 65..122
    private val ruA_ZInterval = 1040..1071
    private val rua_zInterval = 1072..1103

    init {
        val listRuSymbols = listOf(
            "а", "б", "в", "г", "д",
            "е", "ё", "ж", "з", "и",
            "й", "к", "л", "м", "н",
            "о", "п", "р", "с", "т",
            "у", "ф", "х", "ц", "ч",
            "ш", "щ", "ъ", "ы", "ь",
            "э", "ю", "я"
        )
        val listEnSymbols = listOf(
            "a", "b", "v", "g", "d",
            "e", "e", "zh", "z", "i",
            "i", "k", "l", "m", "n",
            "o", "p", "r", "s", "t",
            "u", "f", "h", "c", "ch",
            "sh", "sh'", "", "i", "",
            "e", "yu", "ya"
        )

        for (i in listRuSymbols.indices) {
            mapSymbols[listRuSymbols[i]] = listEnSymbols[i]
        }
    }

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        if (fullName?.contains(Regex("\\w")) != true)
            return null to null

        val parts: List<String> = fullName.split(" ")

        val firstName = parts.getOrNull(0)
        val lastName = parts.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val sb = StringBuilder()

        for (i in payload.indices) {

            val char = payload[i]

            sb.append(
                when (char.code) {
                    in a_zA_ZInterval -> char
                    in ruA_ZInterval -> mapSymbols[char.lowercaseChar().toString()]?.uppercase(
                        Locale.ROOT
                    )
                    in rua_zInterval -> mapSymbols[char.toString()]
                    else -> if(char.isWhitespace())
                        divider
                    else
                        char
                }
            )
        }

        return sb.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? =
        if (firstName?.contains(Regex("\\w")) == true)
            if (lastName?.contains(Regex("\\w")) == true)
                firstName[0].uppercaseChar().toString() +
                        lastName[0].uppercaseChar()
            else
                firstName[0].uppercaseChar().toString()
        else
            null

    fun convertPxToDp(context: Context, px: Int): Int {
        return (px / context.resources.displayMetrics.density).roundToInt()
    }

    fun convertDpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return sp * context.resources.displayMetrics.scaledDensity.roundToInt()
    }
}