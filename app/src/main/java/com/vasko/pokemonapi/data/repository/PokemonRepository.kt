package com.vasko.pokemonapi.data.repository

import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.data.model.PokemonList
import io.reactivex.Single

interface PokemonRepository {
    fun getList(offset: Int, limit: Int): Single<PokemonList>
    fun getDetails(name: String): Single<Pokemon>
}