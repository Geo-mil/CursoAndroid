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
 * @param userNameOrEmailAddress 
 * @param password 
 * @param rememberClient 
 */

data class AuthenticateModel (

    @Json(name = "userNameOrEmailAddress")
    val userNameOrEmailAddress: kotlin.String,

    @Json(name = "password")
    val password: kotlin.String,

    @Json(name = "rememberClient")
    val rememberClient: kotlin.Boolean? = null

)

