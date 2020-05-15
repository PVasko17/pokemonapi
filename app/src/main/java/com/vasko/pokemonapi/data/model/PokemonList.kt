package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonList(
    @field:SerializedName("count") val count: Int = 0,
    @field:SerializedName("next") val next: String? = null,
    @field:SerializedName("previous") val previous: String? = null,
    @field:SerializedName("results") val results: ArrayList<PokemonItem> = ArrayList()
): Parcelable