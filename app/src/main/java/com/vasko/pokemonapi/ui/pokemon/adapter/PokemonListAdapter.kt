package com.vasko.pokemonapi.ui.pokemon.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textview.MaterialTextView
import com.vasko.pokemonapi.R
import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.ui.base.adapter.BaseRecycleListAdapter
import com.vasko.pokemonapi.ui.base.adapter.BaseViewHolder
import com.vasko.pokemonapi.ui.base.adapter.OnRecycleItemClick
import com.vasko.pokemonapi.ui.views.SquareImageView
import com.vasko.pokemonapi.utils.load
import kotlinx.android.extensions.LayoutContainer

class PokemonListAdapter(private val onRecycleItemClick: OnRecycleItemClick<Pokemon>) :
    BaseRecycleListAdapter<PokemonListAdapter.PokemonListViewHolder, Pokemon>() {

    override fun createDataHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            makeView(parent, R.layout.adapter_item_pokemon_list),
            onRecycleItemClick
        )
    }

    override fun onBindDataHolder(holder: PokemonListViewHolder, position: Int, item: Pokemon) {
        holder.textViewPokemonName.text = item.name
        holder.imageViewPokemon.load(item.sprites?.frontDefault)
        if (item.weight != null && item.height != null)
            holder.textViewPokemonDescription.text =
                holder.containerView.context.getString(
                    R.string.pattern_brief_info,
                    item.weight,
                    item.height
                )
    }

    class PokemonListViewHolder(
        override val containerView: View,
        onRecycleItemClick: OnRecycleItemClick<Pokemon>
    ) :
        BaseViewHolder<Pokemon>(containerView, onRecycleItemClick), LayoutContainer,
        View.OnClickListener {

        val imageViewPokemon: SquareImageView = containerView.findViewById(R.id.imageViewPokemon)
        val textViewPokemonName: MaterialTextView = containerView.findViewById(R.id.textViewPokemonName)
        val textViewPokemonDescription: MaterialTextView = containerView.findViewById(R.id.textViewPokemonDescription)
    }
}