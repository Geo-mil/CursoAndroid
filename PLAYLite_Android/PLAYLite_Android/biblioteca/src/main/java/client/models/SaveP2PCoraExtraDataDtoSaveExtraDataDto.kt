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

import org.openapitools.client.models.SaveP2PCoraExtraDataDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param `data` 
 */

data class SaveP2PCoraExtraDataDtoSaveExtraDataDto (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "data")
    val `data`: SaveP2PCoraExtraDataDto? = null

)

