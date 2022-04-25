/**
 * PriluxWebUI API
 *
 * PriluxWebUI
 *
 * The version of the OpenAPI document: v1
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models

import org.openapitools.client.models.CoraFilter

import com.squareup.moshi.Json

/**
 * 
 *
 * @param coraId 
 * @param coraFirmwareUpdateFile 
 * @param launchTime 
 * @param version 
 * @param fileSize 
 * @param allCoras 
 * @param filter 
 */

data class CoraFirmwareUpdateJobDto (

    @Json(name = "coraId")
    val coraId: kotlin.Int,

    @Json(name = "coraFirmwareUpdateFile")
    val coraFirmwareUpdateFile: kotlin.String,

    @Json(name = "launchTime")
    val launchTime: java.time.OffsetDateTime,

    @Json(name = "version")
    val version: kotlin.String,

    @Json(name = "fileSize")
    val fileSize: kotlin.Int,

    @Json(name = "allCoras")
    val allCoras: kotlin.Boolean? = null,

    @Json(name = "filter")
    val filter: CoraFilter? = null

)

