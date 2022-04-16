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

import org.openapitools.client.models.MaintenanceAffectedDevices

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param preventiveMaintenanceParentId 
 * @param observations 
 * @param date 
 * @param amountWorkedTime 
 * @param creationTime 
 * @param tenantId 
 * @param affectedDevices 
 * @param mediaFileIds 
 */

data class CreatePreventiveMaintenanceUpdateDto (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "preventiveMaintenanceParentId")
    val preventiveMaintenanceParentId: kotlin.Int? = null,

    @Json(name = "observations")
    val observations: kotlin.String? = null,

    @Json(name = "date")
    val date: java.time.OffsetDateTime? = null,

    @Json(name = "amountWorkedTime")
    val amountWorkedTime: kotlin.Int? = null,

    @Json(name = "creationTime")
    val creationTime: java.time.OffsetDateTime? = null,

    @Json(name = "tenantId")
    val tenantId: kotlin.Int? = null,

    @Json(name = "affectedDevices")
    val affectedDevices: kotlin.collections.List<MaintenanceAffectedDevices>? = null,

    @Json(name = "mediaFileIds")
    val mediaFileIds: kotlin.collections.List<kotlin.Int>? = null

)

