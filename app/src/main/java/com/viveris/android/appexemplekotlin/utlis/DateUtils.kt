package com.viveris.android.appexemplekotlin.utlis

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        private const val FORMAT_DD_MM_YYYY = "dd/MM/yyyy"

        fun dateToString(dateSec: Int?): String = when (dateSec != null) {
            true -> {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = dateSec * 1000L
                val dateFormat = SimpleDateFormat(FORMAT_DD_MM_YYYY, Locale.getDefault())
                dateFormat.format(calendar.time)
            }
            else -> ""
        }
    }
}
