package com.vasko.pokemonapi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasko.pokemonapi.core.ViewModelFactory
import com.vasko.pokemonapi.ui.pokemon.PokemonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel::class)
    abstract fun pokemonViewModel(viewModel: PokemonViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}