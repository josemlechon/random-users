package com.jml.random.users.common.extensions

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAdjusters

enum class FormatDates(val pattern: String) {
    DATE_FULL("yyyy-MM-dd'T'HH:mm:ss"),
    YEAR_MONTH_DATE("yyyy-MM-dd"),
    DATE_MONTH_YEAR("dd/MM/yyyy")
}

enum class FormatTimes(val pattern: String) {
    DATE_FULL("yyyy-MM-dd'T'HH:mm:ss"),
    TIME("HH:mm")
}


fun String.formattedDate(from: FormatDates, to: FormatDates): String {

    val formatter = DateTimeFormatter.ofPattern(from.pattern)
    val currentDate = LocalDate.parse(this, formatter)

    return DateTimeFormatter.ofPattern(to.pattern).format(currentDate)
}

fun String.formattedTime(from: FormatTimes, to: FormatTimes): String {

    val formatter = DateTimeFormatter.ofPattern(from.pattern)
    val currentTime = LocalTime.parse(this, formatter)

    return DateTimeFormatter.ofPattern(to.pattern).format(currentTime)
}

fun getNextMonday(): LocalDate {
    return LocalDate.now()
        .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
}

fun LocalDate.toFormat(new: FormatDates): String {
    return format(DateTimeFormatter.ofPattern(new.pattern))
}
