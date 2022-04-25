package converters

import Converters
import java.util.*
import java.util.concurrent.TimeUnit

object DateFunctions {

    fun timeZoneActualMinutos(): ByteArray {
        val currentTz = TimeZone.getDefault()
        val milliseconds = currentTz.rawOffset
        val minutosFromGMT = TimeUnit.MILLISECONDS.toMinutes(milliseconds.toLong())

        val minutosFromGMTInt = minutosFromGMT.toInt()

        val lsb = (minutosFromGMTInt and 0xFF).toByte()
        val msb = (minutosFromGMTInt ushr 8 and 0xFF).toByte()

        return byteArrayOf(msb, lsb)
    }


    //funcion para saber si el rango horario esta en DST activado, necesario a la hora de sincronizar la hora
    fun getDSTInRange(): ByteArray {
        val date = Date()
        var byteArray = byteArrayOf()
        val tz = TimeZone.getDefault()
        val inDs = tz.inDaylightTime(date)
        if (inDs == true) {
            byteArray += 1
        } else {
            byteArray += 0
        }

        return byteArray
    }
    fun getDataTimeToBLE(): ByteArray {
        var byteArray = byteArrayOf()
    /*    DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        //That will return 2018-04-16T17:00:08.746Z. For your format, or if you need a different timezone, you can specify those:

        val date = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
*/

        val date2 = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date2
        val year = calendar.get(Calendar.YEAR)
        val month = (calendar.get(Calendar.MONTH) + 1).toByte() //los meses empiezan 0
        val day = calendar.get(Calendar.DAY_OF_MONTH).toByte()
        val weekdayCheck = (calendar.get(Calendar.DAY_OF_WEEK) - 1 ) //Va de 1 a 7 y el Sunday es 1
        var weekday: Byte = 0
        if(weekdayCheck == 0){
            weekday = 7
        } else {
            weekday = weekdayCheck.toByte()
        }
        val hour = calendar.get(Calendar.HOUR_OF_DAY).toByte()
        val minutes = calendar.get(Calendar.MINUTE).toByte()
        val seconds = calendar.get(Calendar.SECOND).toByte()

        byteArray += Converters.intToByteArray(year, 2)
        byteArray += month
        byteArray += day
        byteArray += hour
        byteArray += minutes
        byteArray += seconds
        byteArray += weekday

        return byteArray
    }

}
