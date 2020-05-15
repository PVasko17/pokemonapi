package com.vasko.pokemonapi.data.datasource

import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.data.model.PokemonList
import com.vasko.pokemonapi.data.repository.PokemonRepository
import com.vasko.pokemonapi.data.services.PokemonService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDataSource @Inject constructor(private val pokemonService: PokemonService): PokemonRepository {

    /**
     * Load pokemon list with paging
     *
     * @param offset Int - Paging offset
     * @param limit Int - Number of items per page
     */
    override fun getList(offset: Int, limit: Int): Single<PokemonList> {
        return pokemonService.getList(offset, limit)
            .subscribeOn(Schedulers.io())
            .onErrorReturn { t -> PokemonList(0, null, null, ArrayList()) }
            .map {
                return@map it
            }
    }

    /**
     * Load pokemon details data by name
     *
     * @param name Pokemon name to fetch details
     */
    override fun getDetails(name: String): Single<Pokemon> {
        return pokemonService.getDetails(name)
            .subscribeOn(Schedulers.io())
            .map {
                return@map it
            }
    }

}