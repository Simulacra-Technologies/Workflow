package com.simulacratech.base.utility

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.simulacratech.base.R
import com.simulacratech.base.utility.Constants.Companion.PREF_MASTER_DATA_VERSION

import java.text.SimpleDateFormat
import java.time.Instant.now
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)

fun getISOFormatDate(inMillis: Long): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = inMillis
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        return inputFormat.format(calendar.time)
    } catch (ex: Exception) {
        ex.showLog()
    }
    return ""
}

fun getFormattedDate(millis: Long, format: String = "dd-MM-yyyy"): String {
    val outputFormat = SimpleDateFormat(format, Locale.ENGLISH)
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return outputFormat.format(calendar.time)
    } catch (ex: Exception) {
        ex.showLog()
    }
    return ""
}

fun getNoOfDays(startMillis: Long, endMillis: Long): Long {
    val diff: Long = endMillis - startMillis
    return diff / (24 * 60 * 60 * 1000)
}

fun getNoOfWorkingDays(startMillis: Long, endMillis: Long): Int {
    var count = 0
    val startCalendar = Calendar.getInstance()
    startCalendar.timeInMillis = startMillis
    val endCalendar = Calendar.getInstance()
    endCalendar.timeInMillis = endMillis
    do {
        if (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCalendar.get(
                Calendar.DAY_OF_WEEK
            ) != Calendar.SUNDAY
        ) {
            ++count
        }
        startCalendar.add(Calendar.DAY_OF_MONTH, 1)
    } while (startCalendar.timeInMillis < endCalendar.timeInMillis) //excluding end date
    return count
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDueDateAfterParsing(dueDateString: String?): Long {
    try {
        dueDateString?.let {
            val today = ZonedDateTime.ofInstant(now(), ZoneId.systemDefault())
            val dueDate = ZonedDateTime.parse(
                dueDateString,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
            )
            val hours = ChronoUnit.HOURS.between(today, dueDate)
            val days = ChronoUnit.DAYS.between(today, dueDate)
            val remainingHours = hours % 24

            //showLog("HOURS BETWEEN $dueDateString $days $dueDate $hours $remainingHours")

            return if (remainingHours in 1..23)
                days + 1
            else if (remainingHours > -23 && remainingHours < 0)
                days - 1
            else
                days
        }
    } catch (ex: DateTimeParseException) {
        ex.showLog()
    } catch (ex: Exception) {
        ex.showLog()
    }
    return 0
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.getFormattedDueDate(dueDateString: String?): String {
    try {
        dueDateString?.let {
            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy").withLocale(Locale.US)
            val dueDate = ZonedDateTime.parse(
                it,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
            )
            return dateFormatter.format(dueDate)
        } ?: return getString(R.string.na)
    } catch (ex: DateTimeParseException) {
        showLog(ex.localizedMessage)
        return dueDateString!! //getString(R.string.na)
    } catch (ex: Exception) {
        ex.showLog()
        return getString(R.string.na)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedTime(mContext: Context, dateTime: String): String {
    try {
        val now = ZonedDateTime.ofInstant(now(), ZoneId.systemDefault())
        val weGotDate = ZonedDateTime.parse(
            dateTime,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
        )
        val diffInMillis = ChronoUnit.MILLIS.between(weGotDate, now)
        val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
        val years = days / 365
        val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) - TimeUnit.DAYS.toHours(days)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(diffInMillis)
        )
        return if (minutes <= 60 && hours != 0L) {
            if (years > 0) {
                val text =
                    mContext.resources.getQuantityString(R.plurals.years, years.toInt(), years)
                mContext.getString(R.string.time_ago, years, text)
            } else if (hours <= 60 && days != 0L) {
                val text =
                    mContext.resources.getQuantityString(R.plurals.days, days.toInt(), days)
                mContext.getString(R.string.time_ago, days, text)
            } else {
                val text =
                    mContext.resources.getQuantityString(R.plurals.hours, hours.toInt(), hours)
                mContext.getString(R.string.time_ago, hours, text)
            }
        } else {
            val text = mContext.resources.getQuantityString(
                R.plurals.minutes,
                minutes.toInt(),
                minutes
            )
            mContext.getString(R.string.time_ago, minutes, text)
        }
    } catch (ex: Exception) {
        ex.showLog()
    }
    return ""
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDateAndTime(dateTime: String): String {
    try {
        val weGotDate = ZonedDateTime.parse(
            dateTime,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
        )
        val dateFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a").withLocale(Locale.US)
        return dateFormatter.format(weGotDate)
    } catch (ex: Exception) {
        ex.showLog()
    }
    return ""
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCustomFormattedDate(dateStr: String): ZonedDateTime {
    val dateFormatterSource =
        DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy").withLocale(Locale.getDefault())
    return ZonedDateTime.parse(dateStr, dateFormatterSource)
}

/*
* @param
* dateSelected - Date in UTC format
* */
@RequiresApi(Build.VERSION_CODES.O)
fun getTimeBetweenDueDateAndNow(mContext: Context, dateSelected: String): String {
    try {
        val now = ZonedDateTime.ofInstant(now(), ZoneId.systemDefault())
        val dueDateSelected = ZonedDateTime.parse(
            dateSelected,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
        )
        showLog("NOW: $now SELECTED: $dueDateSelected")
        val diffInMillis = ChronoUnit.MILLIS.between(now, dueDateSelected)
        val days = ChronoUnit.DAYS.between(now, dueDateSelected)
        val years = days / 365
        val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) - TimeUnit.DAYS.toHours(days)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(diffInMillis)
        )
        return if (minutes <= 60 && hours != 0L) {
            if (years > 0) {
                val text =
                    mContext.resources.getQuantityString(R.plurals.years, years.toInt(), years)
                mContext.getString(R.string.complete_due_date, years, text)
            } else if (hours <= 60 && days != 0L) {
                val text =
                    mContext.resources.getQuantityString(R.plurals.days, days.toInt(), days)
                mContext.getString(R.string.complete_due_date, days, text)
            } else {
                val text =
                    mContext.resources.getQuantityString(R.plurals.hours, hours.toInt(), hours)
                mContext.getString(R.string.complete_due_date, hours, text)
            }
        } else {
            val text = mContext.resources.getQuantityString(
                R.plurals.minutes,
                minutes.toInt(),
                minutes
            )
            mContext.getString(R.string.complete_due_date, minutes, text)
        }
    } catch (ex: Exception) {
        ex.showLog()
    }
    return ""
}

private const val DOWNLOAD_HOURS_THRESHOLD = 2
fun isMasterDataDownloadTime(): Boolean {
    try {
        val lastDownloaded =
            PreferenceHelper.instance.getLongData(Constants.PREF_MASTER_DATA_DOWNLOAD_TIME, 0L)
        return if (lastDownloaded == 0L)
            true
        else {
            val todayCalendar = Calendar.getInstance()
            val lastDownloadCalendar = Calendar.getInstance()
            lastDownloadCalendar.timeInMillis = lastDownloaded
            val diff: Long = todayCalendar.timeInMillis - lastDownloadCalendar.timeInMillis
            val days = TimeUnit.MILLISECONDS.toDays(diff)
            val hours = TimeUnit.MILLISECONDS.toHours(diff) - TimeUnit.DAYS.toHours(days)
            val isNewVersion = MASTER_DATA_VERSION > PreferenceHelper.instance.getIntData(
                PREF_MASTER_DATA_VERSION,
                0
            )
            showLog(
                "LAST MASTER DOWNLOADED $hours oldVersion: ${
                    PreferenceHelper.instance.getIntData(
                        PREF_MASTER_DATA_VERSION,
                        0
                    )
                }, newVersion: ${MASTER_DATA_VERSION}, isNewVersion: $isNewVersion"
            )
            hours >= DOWNLOAD_HOURS_THRESHOLD || isNewVersion
        }
    } catch (ex: Exception) {
        ex.showLog()
    }
    return false
}

@RequiresApi(Build.VERSION_CODES.O)
fun getISODateInMillis(date: String): Long {
    var inMillis: Long = 0
    try {
        val dateSelected = ZonedDateTime.parse(
            date,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
        )
        inMillis = dateSelected.toInstant().toEpochMilli()
    } catch (ex: Exception) {
        ex.showLog()
    }
    return inMillis
}

fun Context.convertSecondsToHours(secondsValueInString: String, inShort: Boolean = false): String {
    return try {
        val secondValueInFloat = secondsValueInString.toFloat()
        val hours = (secondValueInFloat / 3600).toInt()
        val remainder = secondValueInFloat - hours * 3600
        showLog("HRS: $hours MINS: $remainder")
        val minutes = (remainder / 60).toInt()
        showLog("FINAL HRS: $hours FINAL MINS: $minutes")
        if (inShort)
            getString(
                R.string.hours_mins_value_short,
                hours.toString(),
                minutes.toString()
            )
        else
            getString(
                R.string.hours_mins_value,
                hours.toString(),
                minutes.toString()
            )
    } catch (ex: Exception) {
        ex.showLog()
        if (inShort)
            getString(R.string.hours_mins_value_short, "0", "0")
        else
            getString(R.string.hours_mins_value, "0", "0")
    }
}

fun todayIsAfterLastWorkingDate(lastWorkingDate: String): Boolean {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val lastDate = format.parse(lastWorkingDate)
    val today = Calendar.getInstance()
    val todayDate = format.parse(format.format(today.time))
    val lastWorkingDateCalendar = Calendar.getInstance()
    lastDate?.let {
        lastWorkingDateCalendar.time = it
        lastWorkingDateCalendar
    }
    showLog(
        "LAST DATE: $lastWorkingDate  ISEQUAL: ${todayDate == lastDate} ISAFTER: ${
            today.after(
                lastWorkingDateCalendar
            )
        }"
    )
    return when {
        todayDate == lastDate -> false

        today.after(lastWorkingDateCalendar) -> true

        else -> false
    }
}

/**
 * @return a [Date] in iso format after adding the passed value of daysToAdd from the current date.
 * @param daysToAdd An integer value to pass the number of days to add from the current date.
 *
 */
fun getISODateWithDaysAdded(daysToAdd: Int): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, daysToAdd)
        return getISOFormatDate(calendar.timeInMillis)
    } catch (ex: Exception) {
        ex.showLog()
    }
    return ""
}