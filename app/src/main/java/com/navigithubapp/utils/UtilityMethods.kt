package com.navigithubapp.utils

import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
class UtilityMethods {

    companion object{
        @JvmStatic
        fun convertToReadableTimeStamp(date: String): String {

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            try {
                val date = inputFormat.parse(date)
                return DateFormat.format("dd MMM yyyy", date) as String
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        }
    }

}