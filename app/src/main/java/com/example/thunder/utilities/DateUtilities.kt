package com.example.thunder.utilities

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun displayFormattedDate(localTime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM, EEEE", Locale.getDefault())

    val parsedDate = LocalDateTime.parse(localTime, inputFormatter)
    return parsedDate.format(outputFormatter)

}


fun displayDate(localTime: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())

    val parsedDate = inputFormatter.parse(localTime)
    return parsedDate?.let { outputFormatter.format(it) } ?: "Invalid date"
}