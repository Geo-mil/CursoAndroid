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

import org.openapitools.client.models.PreventiveMaintenanceStatus

import com.squareup.moshi.Json

/**
 * 
 *
 * @param preventiveMaintenanceId 
 * @param status 
 */

data class ChangePreventiveMaintenanceStatusDto (

    @Json(name = "preventiveMaintenanceId")
    val preventiveMaintenanceId: kotlin.Int? = null,

    @Json(name = "status")
    val status: PreventiveMaintenanceStatus? = null

)
