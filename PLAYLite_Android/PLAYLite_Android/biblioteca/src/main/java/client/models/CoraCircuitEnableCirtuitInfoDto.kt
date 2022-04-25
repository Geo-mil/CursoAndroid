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


import com.squareup.moshi.Json

/**
 * 
 *
 * @param circuitNumber 
 * @param enabled 
 */

data class CoraCircuitEnableCirtuitInfoDto (

    @Json(name = "circuitNumber")
    val circuitNumber: kotlin.Int? = null,

    @Json(name = "enabled")
    val enabled: kotlin.Boolean? = null

)
