package com.prilux.cmr.globals

object DbConstants {
    const val MODEL_CMR_NAME = "prlxSCR"
    const val MODEL_PLC_NAME = "prlxPLC"
    const val MODEL_IOT_NAME = "prlxIoT"
    const val MODEL_PLATFORM = "CORA"
    const val MODEL_LITE = "prlxLITE"
}

fun parseCoraNamePlatformToBle(coraBleName: String, DbConstantName: String): String {
    var parseName = ""

    parseName = coraBleName.replace(DbConstants.MODEL_PLATFORM, DbConstantName, false)
    return parseName
}

fun parseCoraNameBleToPlatform(coraBleName: String, DbConstantName: String, ): String {
    var parseName = ""

    parseName = coraBleName.replace(DbConstantName, DbConstants.MODEL_PLATFORM, false)
    return parseName
}
