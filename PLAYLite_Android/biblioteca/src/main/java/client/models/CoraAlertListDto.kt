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

import org.openapitools.client.models.Alarmstate
import org.openapitools.client.models.AlertType

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param creationTime 
 * @param lastUpDateTime 
 * @param coraID 
 * @param alarmState 
 * @param alertMessage 
 * @param tenantId 
 * @param circuitNumber 
 * @param luminaryLineID 
 * @param alertType 
 */

data class CoraAlertListDto (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "creationTime")
    val creationTime: java.time.OffsetDateTime? = null,

    @Json(name = "lastUpDateTime")
    val lastUpDateTime: java.time.OffsetDateTime? = null,

    @Json(name = "coraID")
    val coraID: kotlin.Int? = null,

    @Json(name = "alarmState")
    val alarmState: Alarmstate? = null,

    @Json(name = "alertMessage")
    val alertMessage: kotlin.String? = null,

    @Json(name = "tenantId")
    val tenantId: kotlin.Int? = null,

    @Json(name = "circuitNumber")
    val circuitNumber: kotlin.Int? = null,

    @Json(name = "luminaryLineID")
    val luminaryLineID: kotlin.Int? = null,

    @Json(name = "alertType")
    val alertType: AlertType? = null

)

