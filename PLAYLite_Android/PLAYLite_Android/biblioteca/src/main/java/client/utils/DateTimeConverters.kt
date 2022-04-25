package client.utils

import android.util.ArrayMap
import android.util.Log
import java.text.ParseException
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DateTimeConverters {


    private val formatters = arrayOf<String>(
        "yyyy-MM-dd'T'HH:mm:ss[.S][.SS][.SSS][.SSSS][.SSSSS][.SSSSSS][ZZZZZ]",
        "yyyy-MM-dd'T'HH:mm:ss[.S][.SS][.SSS][.SSSS][.SSSSS][.SSSSSS]'ZZZZZ'",
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ",
        "yyyy-MM-dd",
        "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ",
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSS",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm:ss 'Z'",
        "yyyy-MM-dd HH:mm:ss Z",
        "yyyy-MM-dd HH:mm:ss.SSS ZZZZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSS ZZZZ",
        "yyyy-MM-dd HH:mm:ss.SSSZZZZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'ZZZZ'",
        "yyyy-MM-dd HH:mm:ss ZZZZ",
        "yyyy-MM-dd'T'HH:mm:ss ZZZZ",
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ",
        "uuuu-MM-dd'T'HH:mm:ss[.SSS]Z",
        "uuuu-MM-dd'T'HH:mm:ss[.SSS][Z]",
        "yyyy-MM-dd'T'HH:mm:ss[.SSS]Z",
        "yyyy-MM-dd'T'HH:mm:ss.SSS[Z]",

    )


    fun getFormatOffsetDateTime (value: String) : OffsetDateTime? {
        val value = value + "+00:00"
        for ((index, pattern) in formatters.withIndex()) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
                try {
                    var dateTime = OffsetDateTime.parse(value, formatter)
                    //Log.e("PARSEDATETIMEOK", "${pattern}-> OK -> ${formatter}")
                    Log.e("INDEX-OK", "${index}")
                    return  dateTime
                } catch (e: Exception) {
                    Log.e("PARSEDATETIME", "${e.message}-> ${formatter}")
                }
        }
        Log.e("INDEX-FAIL", "${value}")
        return null
    }

}