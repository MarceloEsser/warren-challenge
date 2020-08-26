package com.example.warrenchallenge.service.wrapper.resource

data class Resource<T>(
    val requestStatus: Status,
    val data: T? = null,
    val message: String? = null
) {
    companion object {

        fun <T> success(data: T?): Resource<T> = Resource(Status.success, data, null)

        fun <T> error(message: String?, data: T? = null): Resource<T> =
            Resource(Status.error, data, message)

        fun <T> loading(data: T? = null): Resource<T> = Resource(Status.loading, data, null)

    }
}