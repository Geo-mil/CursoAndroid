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
 * @param alertType 
 * @param coraId 
 * @param circuit 
 */

data class CoraAlertCloseDto (

    @Json(name = "alertType")
    val alertType: AlertType? = null,

    @Json(name = "coraId")
    val coraId: kotlin.Int? = null,

    @Json(name = "circuit")
    val circuit: kotlin.Int? = null

)
