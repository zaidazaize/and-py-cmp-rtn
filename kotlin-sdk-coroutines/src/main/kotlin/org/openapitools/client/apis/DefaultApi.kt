package org.openapitools.client.apis

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.squareup.moshi.Json

import org.openapitools.client.models.HTTPValidationError

import okhttp3.MultipartBody

interface DefaultApi {
    /**
     * Handle File Upload
     * 
     * Responses:
     *  - 200: Successful Response
     *  - 422: Validation Error
     *
     * @param file 
     * @return [kotlin.Any]
     */
    @Multipart
    @POST("uploadfile/")
    suspend fun handleFileUploadUploadfilePost(@Part file: MultipartBody.Part): Response<kotlin.Any>

    /**
     * Read Root
     * 
     * Responses:
     *  - 200: Successful Response
     *
     * @return [kotlin.Any]
     */
    @GET("")
    suspend fun readRootGet(): Response<kotlin.Any>

}
