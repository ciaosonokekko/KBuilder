package it.ciaosonokekko.formbuilder.extension

import java.text.SimpleDateFormat
import java.util.*

const val SERVER_FORMAT = "yyyy-MM-dd"
const val SERVER_HOUR_FORMAT = "HH:mm:ss"
const val HUMAN_FORMAT_FULL = "dd/MM/yyyy HH:mm:ss"
const val HUMAN_FORMAT = "dd/MM/yyyy"
const val HUMAN_FORMAT_COMPACT = "dd/MM"
const val HUMAN_HOUR_FORMAT = "HH:mm"

fun Date.toHumanFormatFull(): String {
    val format = SimpleDateFormat(HUMAN_FORMAT_FULL)
    return format.format(this)
}

fun String.toDateFromHumanFormatFull() : Date? {
    val format = SimpleDateFormat(HUMAN_FORMAT_FULL)
    return format.parse(this)
}

fun Date.toServerFormat() : String {
    val format = SimpleDateFormat(SERVER_FORMAT)
    return format.format(this)
}

fun Date.toServerHourFormat() : String {
    val format = SimpleDateFormat(SERVER_HOUR_FORMAT)
    return format.format(this)
}

fun Date.getDaysAgo(daysAgo: Int) : Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

    return calendar.time
}

fun Date.toHumanDate() : String {
    val format = SimpleDateFormat(HUMAN_FORMAT)
    return format.format(this)
}

fun Date.toHumanDateCompact() : String {
    val format = SimpleDateFormat(HUMAN_FORMAT_COMPACT)
    return format.format(this)
}

fun String.toDateFromServer() : Date? {
    val format = SimpleDateFormat(SERVER_FORMAT)
    return format.parse(this)
}

fun String.toDateFromHourServer() : Date? {
    val format = SimpleDateFormat(SERVER_HOUR_FORMAT)
    return format.parse(this)
}

fun Date.toHumanHour() : String {
    val format = SimpleDateFormat(HUMAN_HOUR_FORMAT)
    return format.format(this)
}

fun Date.toHumanDateWithDayOfWeek(): String {
    val date = this.toHumanDate()
    return "${this.getDayOfWeek()} $date"
}

fun Date.toHumanDateCompactWithDayOfWeek(): String {
    val date = this.toHumanDateCompact()
    return "$date ${this.getDayOfWeek().substring(0, 3).capitalize()}"
}

fun Date.getDayOfWeek(): String {
    return SimpleDateFormat("EEEE", Locale.ITALIAN).format(this)
}