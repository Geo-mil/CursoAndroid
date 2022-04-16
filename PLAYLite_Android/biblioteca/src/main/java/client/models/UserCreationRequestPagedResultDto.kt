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

import org.openapitools.client.models.UserCreationRequest

import com.squareup.moshi.Json

/**
 * 
 *
 * @param items 
 * @param totalCount 
 */

data class UserCreationRequestPagedResultDto (

    @Json(name = "items")
    val items: kotlin.collections.List<UserCreationRequest>? = null,

    @Json(name = "totalCount")
    val totalCount: kotlin.Int? = null

)

