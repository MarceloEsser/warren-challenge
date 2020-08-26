package com.example.warrenchallenge.service.wrapper.resource

import com.example.warrenchallenge.service.wrapper.ApiEmptyResult
import com.example.warrenchallenge.service.wrapper.ApiFailureResult
import com.example.warrenchallenge.service.wrapper.ApiResult
import com.example.warrenchallenge.service.wrapper.ApiSuccessResult
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.FlowCollector

class NetworkBoundResource<ResultType, RequestType>(
    private val collector: FlowCollector<Resource<ResultType>>,
    private val call: Deferred<ApiResult<RequestType>>,
    private val processResponse: (response: RequestType?) -> ResultType,
) {

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        collector.emit(Resource.loading())
        fetchFromNetwork()
        return this
    }

    private suspend fun fetchFromNetwork() {
        return when (val result = call.await()) {
            is ApiSuccessResult -> {
                val process = processResponse(result.body)
                collector.emit(Resource.success(process))
            }
            is ApiEmptyResult -> {
                collector.emit(Resource.success(null))
            }
            is ApiFailureResult -> {
                collector.emit(Resource.error(result.message))
            }
        }
    }

}