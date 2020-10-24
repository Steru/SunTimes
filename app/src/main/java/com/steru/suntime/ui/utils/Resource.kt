package com.steru.suntime.ui.utils

import com.steru.suntime.ui.utils.ResourceStatus.*

/*
 * Class taken from Google android/architecture-components-samples repo.
 * (with minor tweak - default null arguments to simplify calls)
 */

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: ResourceStatus, val data: T?, val message: String? = null) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data)
        }
    }
}

/**
 * Possible states of Resource
 */
enum class ResourceStatus {
    SUCCESS,
    ERROR,
    LOADING
}
