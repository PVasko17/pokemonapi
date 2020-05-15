package com.vasko.pokemonapi.di.component

import com.vasko.pokemonapi.di.PerFragment
import com.vasko.pokemonapi.di.module.FragmentModule
import com.vasko.pokemonapi.ui.base.BaseFragment
import com.vasko.pokemonapi.ui.pokemon.fragment.PokemonDetailsFragment
import com.vasko.pokemonapi.ui.pokemon.fragment.PokemonListFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun baseFragment(): BaseFragment
    fun inject(listFragment: PokemonListFragment)
    fun inject(detailsFragment: PokemonDetailsFragment)
}