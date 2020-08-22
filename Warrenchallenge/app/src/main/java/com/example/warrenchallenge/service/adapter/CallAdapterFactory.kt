package com.example.warrenchallenge.service.adapter

import com.example.warrenchallenge.service.wrapper.ApiResult
import kotlinx.coroutines.Deferred
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (getRawType(returnType) == Deferred::class.java) {
            val parameterUpperBound = getParameterUpperBound(0, returnType as ParameterizedType)
            val rawType = getRawType(parameterUpperBound)

            if (rawType != ApiResult::class.java)
                throw IllegalArgumentException("The type must be a ApiResult")

            if (parameterUpperBound !is ParameterizedType)
                throw IllegalArgumentException("resource must be parameterized")


            return MyCallAdapter<Any>(parameterUpperBound)
        }
        throw IllegalArgumentException("return type must be Deferred")
    }
}