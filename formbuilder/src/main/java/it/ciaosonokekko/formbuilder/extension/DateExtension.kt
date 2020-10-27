package it.ciaosonokekko.formbuilder.extension

import java.text.SimpleDateFormat
import java.util.*

const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
const val SERVER_HOUR_FORMAT = "HH:mm:ss"
const val SERVER_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val HUMAN_DATE_FORMAT = "dd/MM/yyyy"
const val HUMAN_HOUR_FORMAT = "HH:mm"
const val HUMAN_FULL_FORMAT = "HH:mm"

fun Date.toStringDate(format: String, default: String? = null): String {
    return try {
        SimpleDateFormat(format, Locale.ITALIAN).format(this)
    } catch (ignore: Exception) {
        default ?: ""
    }
}

fun String.toDate(format: String): Date? {
    return try {
        SimpleDateFormat(format, Locale.ITALIAN).parse(this)
    } catch (ignore: Exception) {
        null
    }
}

fun Date.toServerFullFormat(): String {
    return toStringDate(SERVER_FULL_FORMAT)
}

fun Date.toServerDateFormat(): String {
    return toStringDate(SERVER_DATE_FORMAT)
}

fun Date.toServerHourFormat(): String {
    return toStringDate(SERVER_HOUR_FORMAT)
}

fun Date.toHumanDateFormat(): String {
    return toStringDate(HUMAN_DATE_FORMAT)
}

fun String.toDateFromServerDateFormat(): Date? {
    return toDate(SERVER_DATE_FORMAT)
}

fun String.toDateFromServerHourFormat(): Date? {
    return toDate(SERVER_HOUR_FORMAT)
}

fun String.toDateFromServerFullFormat(): Date? {
    return toDate(SERVER_FULL_FORMAT)
}

fun Date.toHumanHourFormat(): String {
    return toStringDate(HUMAN_HOUR_FORMAT)
}

fun Date.toHumanFullFormat(): String {
    return toStringDate(HUMAN_FULL_FORMAT)
}
