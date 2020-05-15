package com.vasko.pokemonapi.ui.pokemon.adapter

import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.vasko.pokemonapi.R
import com.vasko.pokemonapi.data.model.Type
import com.vasko.pokemonapi.ui.base.adapter.BaseRecycleListAdapter
import com.vasko.pokemonapi.ui.base.adapter.BaseViewHolder
import com.vasko.pokemonapi.ui.base.adapter.OnRecycleItemClick
import kotlinx.android.extensions.LayoutContainer

class PokemonTypesAdapter(private val onRecycleItemClick: OnRecycleItemClick<Type>) :
    BaseRecycleListAdapter<PokemonTypesAdapter.PokemonListViewHolder, Type>() {

    override fun createDataHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            makeView(parent, R.layout.adapter_item_pokemon_types),
            onRecycleItemClick
        )
    }

    override fun onBindDataHolder(holder: PokemonListViewHolder, position: Int, item: Type) {
        holder.textViewTypeName.text = item.type.name
    }

    class PokemonListViewHolder(override val containerView: View,onRecycleItemClick: OnRecycleItemClick<Type>)
        : BaseViewHolder<Type>(containerView, onRecycleItemClick), LayoutContainer, View.OnClickListener {

        val textViewTypeName: MaterialTextView = containerView.findViewById(R.id.textViewTypeName)
        val cardView: MaterialCardView = containerView.findViewById(R.id.cardView)
    }
}