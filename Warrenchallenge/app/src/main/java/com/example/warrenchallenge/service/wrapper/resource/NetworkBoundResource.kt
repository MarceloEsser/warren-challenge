package com.example.warrenchallenge.service.wrapper.resource

import com.example.warrenchallenge.service.wrapper.ApiEmptyResult
import com.example.warrenchallenge.service.wrapper.ApiFailureResult
import com.example.warrenchallenge.service.wrapper.ApiResult
import com.example.warrenchallenge.service.wrapper.ApiSuccessResult
import kotlinx.coroutines.Deferred

abstract class NetworkBoundResource<ResultType, RequestType> {

    suspend fun getResult(): Resource<ResultType> {
        return when (val result = createAsync().await()) {

            is ApiSuccessResult -> Resource.success(processResponse(result.body))

            is ApiEmptyResult -> Resource.success(null)

            is ApiFailureResult -> Resource.error(null)
        }
    }

    protected abstract fun processResponse(response: RequestType?): ResultType

    protected abstract suspend fun createAsync(): Deferred<ApiResult<RequestType>>
}