package com.vasko.pokemonapi.ui.pokemon.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textview.MaterialTextView
import com.vasko.pokemonapi.R
import com.vasko.pokemonapi.data.model.Pokemon
import com.vasko.pokemonapi.data.model.Stat
import com.vasko.pokemonapi.ui.base.adapter.BaseRecycleListAdapter
import com.vasko.pokemonapi.ui.base.adapter.BaseViewHolder
import com.vasko.pokemonapi.ui.base.adapter.OnRecycleItemClick
import com.vasko.pokemonapi.ui.views.SquareImageView
import com.vasko.pokemonapi.utils.load
import kotlinx.android.extensions.LayoutContainer

class PokemonStatsAdapter(private val onRecycleItemClick: OnRecycleItemClick<Stat>) :
    BaseRecycleListAdapter<PokemonStatsAdapter.PokemonListViewHolder, Stat>() {

    override fun createDataHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            makeView(parent, R.layout.adapter_item_pokemon_stats),
            onRecycleItemClick
        )
    }

    override fun onBindDataHolder(holder: PokemonListViewHolder, position: Int, item: Stat) {
        holder.textViewStatName.text = item.stat.name
        holder.textViewStatValue.text = item.baseStat.toString()
    }

    class PokemonListViewHolder(
        override val containerView: View,
        onRecycleItemClick: OnRecycleItemClick<Stat>
    ) :
        BaseViewHolder<Stat>(containerView, onRecycleItemClick), LayoutContainer,
        View.OnClickListener {

        val textViewStatName: MaterialTextView = containerView.findViewById(R.id.textViewStatName)
        val textViewStatValue: MaterialTextView = containerView.findViewById(R.id.textViewStatValue)
    }
}