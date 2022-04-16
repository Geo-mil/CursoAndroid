package org.openapitools.client.infrastructure

import client.utils.DateTimeConverters
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class OffsetDateTimeAdapter {
    @ToJson
    fun toJson(value: OffsetDateTime): String {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value)
    }

    @FromJson
    fun fromJson(value: String): OffsetDateTime? {

        val converter = DateTimeConverters()
        val formatter = converter.getFormatOffsetDateTime(value)
        return formatter
       /* val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        val formatter = DateTimeFormatter.ofPattern(pattern);
        val dt = OffsetDateTime.parse(value, formatter);

        return dt
        //return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)*/
        //return OffsetDateTime.parse(value, formatter)
    }

}
