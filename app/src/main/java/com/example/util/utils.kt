package com.example.util

import java.text.SimpleDateFormat
import java.util.*

fun formatdate(time : Long): String{
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault())
    return format.format(date)
}