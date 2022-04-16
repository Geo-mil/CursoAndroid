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

package org.openapitools.client.apis

import java.io.IOException

import org.openapitools.client.models.IOTCoraGroupCreateDto
import org.openapitools.client.models.IOTCoraGroupDto
import org.openapitools.client.models.IOTCoraGroupDtoPagedResultDto

import com.squareup.moshi.Json

import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ApiResponse
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ClientError
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.infrastructure.ServerError
import org.openapitools.client.infrastructure.MultiValueMap
import org.openapitools.client.infrastructure.RequestConfig
import org.openapitools.client.infrastructure.RequestMethod
import org.openapitools.client.infrastructure.ResponseType
import org.openapitools.client.infrastructure.Success
import org.openapitools.client.infrastructure.toMultiValue

class IOTCoraGroupApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
    * 
    * 
    * @param ioTCoraGroupCreateDto  (optional)
    * @return IOTCoraGroupDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppIOTCoraGroupCreatePost(ioTCoraGroupCreateDto: IOTCoraGroupCreateDto?) : IOTCoraGroupDto {
        val localVarResponse = apiServicesAppIOTCoraGroupCreatePostWithHttpInfo(ioTCoraGroupCreateDto = ioTCoraGroupCreateDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IOTCoraGroupDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param ioTCoraGroupCreateDto  (optional)
    * @return ApiResponse<IOTCoraGroupDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppIOTCoraGroupCreatePostWithHttpInfo(ioTCoraGroupCreateDto: IOTCoraGroupCreateDto?) : ApiResponse<IOTCoraGroupDto?> {
        val localVariableConfig = apiServicesAppIOTCoraGroupCreatePostRequestConfig(ioTCoraGroupCreateDto = ioTCoraGroupCreateDto)

        return request<IOTCoraGroupCreateDto, IOTCoraGroupDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppIOTCoraGroupCreatePost
    *
    * @param ioTCoraGroupCreateDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppIOTCoraGroupCreatePostRequestConfig(ioTCoraGroupCreateDto: IOTCoraGroupCreateDto?) : RequestConfig<IOTCoraGroupCreateDto> {
        val localVariableBody = ioTCoraGroupCreateDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/IOTCoraGroup/Create",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param groupId  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppIOTCoraGroupDeleteIOTGroupDelete(groupId: kotlin.Int?) : Unit {
        val localVarResponse = apiServicesAppIOTCoraGroupDeleteIOTGroupDeleteWithHttpInfo(groupId = groupId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param groupId  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppIOTCoraGroupDeleteIOTGroupDeleteWithHttpInfo(groupId: kotlin.Int?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppIOTCoraGroupDeleteIOTGroupDeleteRequestConfig(groupId = groupId)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppIOTCoraGroupDeleteIOTGroupDelete
    *
    * @param groupId  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppIOTCoraGroupDeleteIOTGroupDeleteRequestConfig(groupId: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (groupId != null) {
                    put("groupId", listOf(groupId.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        
        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/services/app/IOTCoraGroup/DeleteIOTGroup",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param maxResultCount  (optional)
    * @param skipCount  (optional)
    * @return IOTCoraGroupDtoPagedResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppIOTCoraGroupGetAllGet(maxResultCount: kotlin.Int?, skipCount: kotlin.Int?) : IOTCoraGroupDtoPagedResultDto {
        val localVarResponse = apiServicesAppIOTCoraGroupGetAllGetWithHttpInfo(maxResultCount = maxResultCount, skipCount = skipCount)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IOTCoraGroupDtoPagedResultDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param maxResultCount  (optional)
    * @param skipCount  (optional)
    * @return ApiResponse<IOTCoraGroupDtoPagedResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppIOTCoraGroupGetAllGetWithHttpInfo(maxResultCount: kotlin.Int?, skipCount: kotlin.Int?) : ApiResponse<IOTCoraGroupDtoPagedResultDto?> {
        val localVariableConfig = apiServicesAppIOTCoraGroupGetAllGetRequestConfig(maxResultCount = maxResultCount, skipCount = skipCount)

        return request<Unit, IOTCoraGroupDtoPagedResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppIOTCoraGroupGetAllGet
    *
    * @param maxResultCount  (optional)
    * @param skipCount  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppIOTCoraGroupGetAllGetRequestConfig(maxResultCount: kotlin.Int?, skipCount: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (maxResultCount != null) {
                    put("MaxResultCount", listOf(maxResultCount.toString()))
                }
                if (skipCount != null) {
                    put("SkipCount", listOf(skipCount.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/IOTCoraGroup/GetAll",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param groupId  (optional)
    * @return IOTCoraGroupDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppIOTCoraGroupGetGet(groupId: kotlin.Int?) : IOTCoraGroupDto {
        val localVarResponse = apiServicesAppIOTCoraGroupGetGetWithHttpInfo(groupId = groupId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IOTCoraGroupDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param groupId  (optional)
    * @return ApiResponse<IOTCoraGroupDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppIOTCoraGroupGetGetWithHttpInfo(groupId: kotlin.Int?) : ApiResponse<IOTCoraGroupDto?> {
        val localVariableConfig = apiServicesAppIOTCoraGroupGetGetRequestConfig(groupId = groupId)

        return request<Unit, IOTCoraGroupDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppIOTCoraGroupGetGet
    *
    * @param groupId  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppIOTCoraGroupGetGetRequestConfig(groupId: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (groupId != null) {
                    put("groupId", listOf(groupId.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/IOTCoraGroup/Get",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}
