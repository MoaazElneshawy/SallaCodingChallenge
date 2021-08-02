package com.moaazelneshawy.sallacodechallenge.data.network

/**
 * Amer Elsayed on 1/22/2021.
 * dev.amer.elsayed@gmail.com
 * https://amerelsayedn1.github.io/AmerElsayed
 */

data class Resource<out T>(
    @Status val status: Int,
    val message: String? = null,
    val response: T? = null
) {

    companion object {

        fun loading(): Resource<Nothing> = Resource(LOADING)

        fun <T> success(data: T?): Resource<T> =
            Resource(status = SUCCESS, response = data)

        fun failure(message: String): Resource<Nothing> =
            Resource(status = ERROR, message = message)
    }
}