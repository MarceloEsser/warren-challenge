package com.example.warrenchallenge.service.wrapper

import com.example.warrenchallenge.Constants
import org.json.JSONObject
import retrofit2.Response

sealed class ApiResult<T> {
    companion object {

        fun <T> create(throwable: Throwable): ApiFailureResult<T> {
            return ApiFailureResult(throwable.message)
        }

        fun <T> create(response: Response<T>): ApiResult<T> {
            if (response.isSuccessful) {

                if (responseIsNotEmpty(response)) {
                    return ApiSuccessResult(response.body())
                }

                return ApiEmptyResult()

            }

            if (containsMessage(response)) {
                val jsonObject = getJsonObjectFrom(response)
                return ApiFailureResult(jsonObject[Constants.Api.message] as? String)
            }

            return ApiFailureResult(Constants.Messages.internalError)

        }

        private fun <T> getJsonObjectFrom(response: Response<T>): JSONObject {
            return JSONObject(
                response.errorBody()?.charStream()?.readText()
                    ?: Constants.Messages.internalError
            )
        }

        private fun <T> containsMessage(response: Response<T>) =
            response.errorBody()?.charStream()?.readText()?.contains(Constants.Api.message) ?: false

        private fun <T> responseIsNotEmpty(response: Response<T>) =
            response.body() != null && response.code() != 204
    }
}

class ApiEmptyResult<T> : ApiResult<T>()
data class ApiFailureResult<T>(val message: String?) : ApiResult<T>()
data class ApiSuccessResult<T>(val body: T?) : ApiResult<T>()