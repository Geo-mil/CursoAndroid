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
 * @param niveles 
 * @param identificador 
 * @param tiempos 
 */

data class DatosSequence (

    @Json(name = "niveles")
    val niveles: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "identificador")
    val identificador: kotlin.Int? = null,

    @Json(name = "tiempos")
    val tiempos: kotlin.collections.List<kotlin.Int>? = null

)
