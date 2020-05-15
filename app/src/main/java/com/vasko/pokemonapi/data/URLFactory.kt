package com.vasko.pokemonapi.data

import okhttp3.HttpUrl

object URLFactory {
    private const val SCHEME = "https"
    private val HOST = "pokeapi.co"
    private val API_PATH = "api/v2/"

    /**
     * HTTP URL builder
     *
     * @return [HttpUrl] object to make REST API requests
     */
    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegments(API_PATH)
            .build()
    }

    object Endpoint {
        const val LIST = "pokemon"
        const val DETAILS = "pokemon/{name}"
    }
}