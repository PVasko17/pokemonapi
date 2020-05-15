package com.vasko.pokemonapi.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonItem(
    @field:SerializedName("name") override val name: String,
    @field:SerializedName("url") override val url: String,
    var pokemon: Pokemon?
): IBaseItem