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

import org.openapitools.client.models.CreatePreventiveMaintenanceUpdateDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param items 
 */

data class CreatePreventiveMaintenanceUpdateDtoListResultDto (

    @Json(name = "items")
    val items: kotlin.collections.List<CreatePreventiveMaintenanceUpdateDto>? = null

)

