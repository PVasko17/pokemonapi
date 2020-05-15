package com.vasko.pokemonapi.data.services

import com.vasko.pokemonapi.data.URLFactory
import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.data.model.PokemonList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(URLFactory.Endpoint.LIST)
    fun getList(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<PokemonList>

    @GET(URLFactory.Endpoint.DETAILS)
    fun getDetails(@Path("name") name: String): Single<Pokemon>
}