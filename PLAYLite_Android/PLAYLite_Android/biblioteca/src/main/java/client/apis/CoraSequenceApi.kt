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

import org.openapitools.client.models.CoraDefaultSequenceDataDtoListResultDto
import org.openapitools.client.models.CoraSequenceDto
import org.openapitools.client.models.CoraSequenceDtoPagedResultDto
import org.openapitools.client.models.ExpectedProgrammingTimeOutput

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

class CoraSequenceApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
    * 
    * 
    * @param coraSequenceDto  (optional)
    * @return CoraSequenceDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceCreatePost(coraSequenceDto: CoraSequenceDto?) : CoraSequenceDto {
        val localVarResponse = apiServicesAppCoraSequenceCreatePostWithHttpInfo(coraSequenceDto = coraSequenceDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraSequenceDto
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
    * @param coraSequenceDto  (optional)
    * @return ApiResponse<CoraSequenceDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceCreatePostWithHttpInfo(coraSequenceDto: CoraSequenceDto?) : ApiResponse<CoraSequenceDto?> {
        val localVariableConfig = apiServicesAppCoraSequenceCreatePostRequestConfig(coraSequenceDto = coraSequenceDto)

        return request<CoraSequenceDto, CoraSequenceDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceCreatePost
    *
    * @param coraSequenceDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceCreatePostRequestConfig(coraSequenceDto: CoraSequenceDto?) : RequestConfig<CoraSequenceDto> {
        val localVariableBody = coraSequenceDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/CoraSequence/Create",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceDeleteDelete(id: kotlin.Int?) : Unit {
        val localVarResponse = apiServicesAppCoraSequenceDeleteDeleteWithHttpInfo(id = id)

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
    * @param id  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceDeleteDeleteWithHttpInfo(id: kotlin.Int?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppCoraSequenceDeleteDeleteRequestConfig(id = id)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceDeleteDelete
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceDeleteDeleteRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (id != null) {
                    put("Id", listOf(id.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        
        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/services/app/CoraSequence/Delete",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param coraSequenceDto  (optional)
    * @return kotlin.Int
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceExpectedProgramingTimePost(coraSequenceDto: CoraSequenceDto?) : kotlin.Int {
        val localVarResponse = apiServicesAppCoraSequenceExpectedProgramingTimePostWithHttpInfo(coraSequenceDto = coraSequenceDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.Int
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
    * @param coraSequenceDto  (optional)
    * @return ApiResponse<kotlin.Int?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceExpectedProgramingTimePostWithHttpInfo(coraSequenceDto: CoraSequenceDto?) : ApiResponse<kotlin.Int?> {
        val localVariableConfig = apiServicesAppCoraSequenceExpectedProgramingTimePostRequestConfig(coraSequenceDto = coraSequenceDto)

        return request<CoraSequenceDto, kotlin.Int>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceExpectedProgramingTimePost
    *
    * @param coraSequenceDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceExpectedProgramingTimePostRequestConfig(coraSequenceDto: CoraSequenceDto?) : RequestConfig<CoraSequenceDto> {
        val localVariableBody = coraSequenceDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/CoraSequence/ExpectedProgramingTime",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param coraSequenceDto  (optional)
    * @return ExpectedProgrammingTimeOutput
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceExpectedProgramingTimeReturnObjectPost(coraSequenceDto: CoraSequenceDto?) : ExpectedProgrammingTimeOutput {
        val localVarResponse = apiServicesAppCoraSequenceExpectedProgramingTimeReturnObjectPostWithHttpInfo(coraSequenceDto = coraSequenceDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as ExpectedProgrammingTimeOutput
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
    * @param coraSequenceDto  (optional)
    * @return ApiResponse<ExpectedProgrammingTimeOutput?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceExpectedProgramingTimeReturnObjectPostWithHttpInfo(coraSequenceDto: CoraSequenceDto?) : ApiResponse<ExpectedProgrammingTimeOutput?> {
        val localVariableConfig = apiServicesAppCoraSequenceExpectedProgramingTimeReturnObjectPostRequestConfig(coraSequenceDto = coraSequenceDto)

        return request<CoraSequenceDto, ExpectedProgrammingTimeOutput>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceExpectedProgramingTimeReturnObjectPost
    *
    * @param coraSequenceDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceExpectedProgramingTimeReturnObjectPostRequestConfig(coraSequenceDto: CoraSequenceDto?) : RequestConfig<CoraSequenceDto> {
        val localVariableBody = coraSequenceDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/CoraSequence/ExpectedProgramingTimeReturnObject",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param keyword  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return CoraSequenceDtoPagedResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceGetAllGet(keyword: kotlin.String?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : CoraSequenceDtoPagedResultDto {
        val localVarResponse = apiServicesAppCoraSequenceGetAllGetWithHttpInfo(keyword = keyword, skipCount = skipCount, maxResultCount = maxResultCount)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraSequenceDtoPagedResultDto
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
    * @param keyword  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return ApiResponse<CoraSequenceDtoPagedResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceGetAllGetWithHttpInfo(keyword: kotlin.String?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : ApiResponse<CoraSequenceDtoPagedResultDto?> {
        val localVariableConfig = apiServicesAppCoraSequenceGetAllGetRequestConfig(keyword = keyword, skipCount = skipCount, maxResultCount = maxResultCount)

        return request<Unit, CoraSequenceDtoPagedResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceGetAllGet
    *
    * @param keyword  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceGetAllGetRequestConfig(keyword: kotlin.String?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (keyword != null) {
                    put("Keyword", listOf(keyword.toString()))
                }
                if (skipCount != null) {
                    put("SkipCount", listOf(skipCount.toString()))
                }
                if (maxResultCount != null) {
                    put("MaxResultCount", listOf(maxResultCount.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/CoraSequence/GetAll",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @return CoraDefaultSequenceDataDtoListResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceGetDefaultSequenceDataGet() : CoraDefaultSequenceDataDtoListResultDto {
        val localVarResponse = apiServicesAppCoraSequenceGetDefaultSequenceDataGetWithHttpInfo()

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraDefaultSequenceDataDtoListResultDto
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
    * @return ApiResponse<CoraDefaultSequenceDataDtoListResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceGetDefaultSequenceDataGetWithHttpInfo() : ApiResponse<CoraDefaultSequenceDataDtoListResultDto?> {
        val localVariableConfig = apiServicesAppCoraSequenceGetDefaultSequenceDataGetRequestConfig()

        return request<Unit, CoraDefaultSequenceDataDtoListResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceGetDefaultSequenceDataGet
    *
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceGetDefaultSequenceDataGetRequestConfig() : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/CoraSequence/GetDefaultSequenceData",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return CoraSequenceDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceGetGet(id: kotlin.Int?) : CoraSequenceDto {
        val localVarResponse = apiServicesAppCoraSequenceGetGetWithHttpInfo(id = id)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraSequenceDto
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
    * @param id  (optional)
    * @return ApiResponse<CoraSequenceDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceGetGetWithHttpInfo(id: kotlin.Int?) : ApiResponse<CoraSequenceDto?> {
        val localVariableConfig = apiServicesAppCoraSequenceGetGetRequestConfig(id = id)

        return request<Unit, CoraSequenceDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceGetGet
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceGetGetRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (id != null) {
                    put("Id", listOf(id.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/CoraSequence/Get",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param coraSequenceDto  (optional)
    * @return CoraSequenceDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppCoraSequenceUpdatePut(coraSequenceDto: CoraSequenceDto?) : CoraSequenceDto {
        val localVarResponse = apiServicesAppCoraSequenceUpdatePutWithHttpInfo(coraSequenceDto = coraSequenceDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraSequenceDto
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
    * @param coraSequenceDto  (optional)
    * @return ApiResponse<CoraSequenceDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppCoraSequenceUpdatePutWithHttpInfo(coraSequenceDto: CoraSequenceDto?) : ApiResponse<CoraSequenceDto?> {
        val localVariableConfig = apiServicesAppCoraSequenceUpdatePutRequestConfig(coraSequenceDto = coraSequenceDto)

        return request<CoraSequenceDto, CoraSequenceDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppCoraSequenceUpdatePut
    *
    * @param coraSequenceDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppCoraSequenceUpdatePutRequestConfig(coraSequenceDto: CoraSequenceDto?) : RequestConfig<CoraSequenceDto> {
        val localVariableBody = coraSequenceDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.PUT,
            path = "/api/services/app/CoraSequence/Update",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}
