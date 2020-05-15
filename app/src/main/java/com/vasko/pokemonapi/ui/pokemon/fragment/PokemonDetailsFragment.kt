package com.vasko.pokemonapi.ui.pokemon.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasko.pokemonapi.R
import com.vasko.pokemonapi.data.model.BaseItem
import com.vasko.pokemonapi.data.model.Stat
import com.vasko.pokemonapi.data.model.Type
import com.vasko.pokemonapi.di.component.FragmentComponent
import com.vasko.pokemonapi.ui.base.BaseFragment
import com.vasko.pokemonapi.ui.base.adapter.OnRecycleItemClick
import com.vasko.pokemonapi.ui.pokemon.adapter.PokemonTypesAdapter
import com.vasko.pokemonapi.ui.pokemon.adapter.PokemonStatsAdapter
import com.vasko.pokemonapi.utils.load
import kotlinx.android.synthetic.main.fragment_pokemon_details.*

/**
 * A simple [BaseFragment] subclass.
 */
class PokemonDetailsFragment : BaseFragment() {

    override fun createLayout(): Int = R.layout.fragment_pokemon_details

    override fun inject(fragmentComponent: FragmentComponent) = component.inject(this)

    private val safeArgs: PokemonDetailsFragmentArgs by navArgs()

    /**
     * Collecting pokemon details data received from the caller fragment
     */
    private val pokemonDetails by lazy { safeArgs.pokemon }

    /**
     * Adapter to display pokemon stats list
     */
    private val statsAdapter by lazy {
        PokemonStatsAdapter(object: OnRecycleItemClick<Stat> {
            override fun onClick(t: Stat, view: View) { }
        })
    }

    /**
     * Adapter to display pokemon types
     */
    private val typesAdapter by lazy {
        PokemonTypesAdapter(object: OnRecycleItemClick<Type> {
            override fun onClick(t: Type, view: View) { }
        })
    }

    override fun bindViewModel() { }

    override fun bindData() {
//        Initialising Stats RecyclerView
        recycleViewStats.apply {
            adapter = statsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = true
        }

//        Initialising Types RecyclerView
        recycleViewType.apply {
            adapter = typesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

//        Populating screen with pokemon info
        fillData()
    }

    /**
     * Populate fragment with data
     */
    private fun fillData() {
        requireActivity().actionBar?.title = pokemonDetails.name
        imageViewAvatar.load(pokemonDetails.sprites?.frontDefault)
        textViewPokemonName.text = pokemonDetails.name
        textViewPokemonDescription.text = getString(
            R.string.pattern_brief_info,
            pokemonDetails.weight,
            pokemonDetails.height
        )

        statsAdapter.items = pokemonDetails.stats
        typesAdapter.items = pokemonDetails.types
    }

}
