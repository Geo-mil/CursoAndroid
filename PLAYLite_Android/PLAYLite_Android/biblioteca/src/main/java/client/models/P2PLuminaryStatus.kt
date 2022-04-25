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
 * Values: _0,_1,_2,_3,_4,_5,_11,_12,_13,_14
 */

enum class P2PLuminaryStatus(val value: kotlin.Int) {

    @Json(name = "0")
    _0(0),

    @Json(name = "1")
    _1(1),

    @Json(name = "2")
    _2(2),

    @Json(name = "3")
    _3(3),

    @Json(name = "4")
    _4(4),

    @Json(name = "5")
    _5(5),

    @Json(name = "11")
    _11(11),

    @Json(name = "12")
    _12(12),

    @Json(name = "13")
    _13(13),

    @Json(name = "14")
    _14(14);

    /**
     * Override toString() to avoid using the enum variable name as the value, and instead use
     * the actual value defined in the API spec file.
     *
     * This solves a problem when the variable name and its value are different, and ensures that
     * the client sends the correct enum values to the server always.
     */
    override fun toString(): String = value.toString()

    companion object {
        /**
         * Converts the provided [data] to a [String] on success, null otherwise.
         */
        fun encode(data: kotlin.Any?): kotlin.String? = if (data is P2PLuminaryStatus) "$data" else null

        /**
         * Returns a valid [P2PLuminaryStatus] for [data], null otherwise.
         */
        fun decode(data: kotlin.Any?): P2PLuminaryStatus? = data?.let {
          val normalizedData = "$it".lowercase()
          values().firstOrNull { value ->
            it == value || normalizedData == "$value".lowercase()
          }
        }
    }
}

