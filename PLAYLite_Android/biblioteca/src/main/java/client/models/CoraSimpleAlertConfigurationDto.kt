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

import org.openapitools.client.models.AlertType

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param alertType 
 * @param minValue 
 * @param maxValue 
 * @param tenantId 
 */

data class CoraSimpleAlertConfigurationDto (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "alertType")
    val alertType: AlertType? = null,

    @Json(name = "minValue")
    val minValue: kotlin.Double? = null,

    @Json(name = "maxValue")
    val maxValue: kotlin.Double? = null,

    @Json(name = "tenantId")
    val tenantId: kotlin.Int? = null

)

