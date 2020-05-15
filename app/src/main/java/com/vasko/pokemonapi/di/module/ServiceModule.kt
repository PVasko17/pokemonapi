package com.vasko.pokemonapi.di.module

import com.vasko.pokemonapi.data.datasource.PokemonDataSource
import com.vasko.pokemonapi.data.repository.PokemonRepository
import com.vasko.pokemonapi.data.services.PokemonService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun providePokemonRepository(pokemonDataSource: PokemonDataSource): PokemonRepository = pokemonDataSource

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService = retrofit.create(PokemonService::class.java)
}