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
 * @param accessToken 
 * @param encryptedAccessToken 
 * @param expireInSeconds 
 * @param waitingForActivation 
 */

data class ExternalAuthenticateResultModel (

    @Json(name = "accessToken")
    val accessToken: kotlin.String? = null,

    @Json(name = "encryptedAccessToken")
    val encryptedAccessToken: kotlin.String? = null,

    @Json(name = "expireInSeconds")
    val expireInSeconds: kotlin.Int? = null,

    @Json(name = "waitingForActivation")
    val waitingForActivation: kotlin.Boolean? = null

)

