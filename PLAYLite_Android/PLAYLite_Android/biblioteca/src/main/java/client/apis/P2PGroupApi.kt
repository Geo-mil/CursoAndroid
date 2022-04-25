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

import org.openapitools.client.models.P2PCreateGroupDto
import org.openapitools.client.models.P2PGroupDto
import org.openapitools.client.models.P2PGroupDtoPagedResultDto

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

class P2PGroupApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
    * 
    * 
    * @param p2PCreateGroupDto  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PGroupCreateP2PNodeGroupPost(p2PCreateGroupDto: P2PCreateGroupDto?) : Unit {
        val localVarResponse = apiServicesAppP2PGroupCreateP2PNodeGroupPostWithHttpInfo(p2PCreateGroupDto = p2PCreateGroupDto)

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
    * @param p2PCreateGroupDto  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PGroupCreateP2PNodeGroupPostWithHttpInfo(p2PCreateGroupDto: P2PCreateGroupDto?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppP2PGroupCreateP2PNodeGroupPostRequestConfig(p2PCreateGroupDto = p2PCreateGroupDto)

        return request<P2PCreateGroupDto, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PGroupCreateP2PNodeGroupPost
    *
    * @param p2PCreateGroupDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PGroupCreateP2PNodeGroupPostRequestConfig(p2PCreateGroupDto: P2PCreateGroupDto?) : RequestConfig<P2PCreateGroupDto> {
        val localVariableBody = p2PCreateGroupDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        
        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/P2PGroup/CreateP2PNodeGroup",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param nodeGroupId  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PGroupDeleteP2PNodeGroupDelete(nodeGroupId: kotlin.Int?) : Unit {
        val localVarResponse = apiServicesAppP2PGroupDeleteP2PNodeGroupDeleteWithHttpInfo(nodeGroupId = nodeGroupId)

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
    * @param nodeGroupId  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PGroupDeleteP2PNodeGroupDeleteWithHttpInfo(nodeGroupId: kotlin.Int?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppP2PGroupDeleteP2PNodeGroupDeleteRequestConfig(nodeGroupId = nodeGroupId)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PGroupDeleteP2PNodeGroupDelete
    *
    * @param nodeGroupId  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PGroupDeleteP2PNodeGroupDeleteRequestConfig(nodeGroupId: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (nodeGroupId != null) {
                    put("nodeGroupId", listOf(nodeGroupId.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        
        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/services/app/P2PGroup/DeleteP2PNodeGroup",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param groupId  (optional)
    * @return P2PGroupDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PGroupGetGet(groupId: kotlin.Int?) : P2PGroupDto {
        val localVarResponse = apiServicesAppP2PGroupGetGetWithHttpInfo(groupId = groupId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as P2PGroupDto
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
    * @return ApiResponse<P2PGroupDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PGroupGetGetWithHttpInfo(groupId: kotlin.Int?) : ApiResponse<P2PGroupDto?> {
        val localVariableConfig = apiServicesAppP2PGroupGetGetRequestConfig(groupId = groupId)

        return request<Unit, P2PGroupDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PGroupGetGet
    *
    * @param groupId  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PGroupGetGetRequestConfig(groupId: kotlin.Int?) : RequestConfig<Unit> {
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
            path = "/api/services/app/P2PGroup/Get",
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
    * @return P2PGroupDtoPagedResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PGroupGetGroupsGet(maxResultCount: kotlin.Int?, skipCount: kotlin.Int?) : P2PGroupDtoPagedResultDto {
        val localVarResponse = apiServicesAppP2PGroupGetGroupsGetWithHttpInfo(maxResultCount = maxResultCount, skipCount = skipCount)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as P2PGroupDtoPagedResultDto
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
    * @return ApiResponse<P2PGroupDtoPagedResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PGroupGetGroupsGetWithHttpInfo(maxResultCount: kotlin.Int?, skipCount: kotlin.Int?) : ApiResponse<P2PGroupDtoPagedResultDto?> {
        val localVariableConfig = apiServicesAppP2PGroupGetGroupsGetRequestConfig(maxResultCount = maxResultCount, skipCount = skipCount)

        return request<Unit, P2PGroupDtoPagedResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PGroupGetGroupsGet
    *
    * @param maxResultCount  (optional)
    * @param skipCount  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PGroupGetGroupsGetRequestConfig(maxResultCount: kotlin.Int?, skipCount: kotlin.Int?) : RequestConfig<Unit> {
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
            path = "/api/services/app/P2PGroup/GetGroups",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param groupId  (optional)
    * @param p2PCreateGroupDto  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PGroupUpdateP2PNodeGroupPut(groupId: kotlin.Int?, p2PCreateGroupDto: P2PCreateGroupDto?) : Unit {
        val localVarResponse = apiServicesAppP2PGroupUpdateP2PNodeGroupPutWithHttpInfo(groupId = groupId, p2PCreateGroupDto = p2PCreateGroupDto)

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
    * @param p2PCreateGroupDto  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PGroupUpdateP2PNodeGroupPutWithHttpInfo(groupId: kotlin.Int?, p2PCreateGroupDto: P2PCreateGroupDto?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppP2PGroupUpdateP2PNodeGroupPutRequestConfig(groupId = groupId, p2PCreateGroupDto = p2PCreateGroupDto)

        return request<P2PCreateGroupDto, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PGroupUpdateP2PNodeGroupPut
    *
    * @param groupId  (optional)
    * @param p2PCreateGroupDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PGroupUpdateP2PNodeGroupPutRequestConfig(groupId: kotlin.Int?, p2PCreateGroupDto: P2PCreateGroupDto?) : RequestConfig<P2PCreateGroupDto> {
        val localVariableBody = p2PCreateGroupDto
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (groupId != null) {
                    put("groupId", listOf(groupId.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        
        return RequestConfig(
            method = RequestMethod.PUT,
            path = "/api/services/app/P2PGroup/UpdateP2PNodeGroup",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}
