package com.zulfahmi.kadefinalproject.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun String.formatDate(fromDateFormat:String="dd/MM/yy", toDateFormat:String = "E, dd MMM yyyy") : String {
    val date = SimpleDateFormat(fromDateFormat).parse(this)
    val dateFormatter = SimpleDateFormat(toDateFormat)
    return dateFormatter.format(date)
}

private fun formatDate(date: String, format: String): String {
    val old = SimpleDateFormat("HH:mm:ss", Locale.US)
    old.timeZone = TimeZone.getTimeZone("UTC")
    val oldDate = old.parse(date)
    val newFormat = SimpleDateFormat(format, Locale.US)
    return newFormat.format(oldDate)
}

fun getTimeFormat(date: String?): String {
    return formatDate(date.toString(), "HH:mm")
}

fun String.parse(delimiter: String = "; ", replacement: String = ";\n" ) : String {
    return this.replace(delimiter, replacement)
}

fun String.parseGoals(delimiter: String = ";", replacement: String = ";\n") : String {
    return this.replace(delimiter, replacement)
}