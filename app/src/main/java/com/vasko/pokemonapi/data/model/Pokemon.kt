package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @field:SerializedName("abilities") val abilities: ArrayList<AbilityItem>? = null,
    @field:SerializedName("base_experience") val baseExperience: Int? = null,
    @field:SerializedName("forms") val forms: ArrayList<BaseItem>? = null,
    @field:SerializedName("game_indices") val gameIndices: ArrayList<GameIndex>? = null,
    @field:SerializedName("height") val height: Int? = null,
    @field:SerializedName("held_items") val heldItems: ArrayList<HeldItem>? = null,
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("is_default") val isDefault: Boolean? = null,
    @field:SerializedName("location_area_encounters") val locationAreaEncounters: String? = null,
    @field:SerializedName("moves") val moves: ArrayList<Move>? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("order") val order: Int? = null,
    @field:SerializedName("species") val species: BaseItem? = null,
    @field:SerializedName("sprites") val sprites: Sprites? = null,
    @field:SerializedName("stats") val stats: ArrayList<Stat>? = null,
    @field:SerializedName("types") val types: ArrayList<Type>? = null,
    @field:SerializedName("weight") val weight: Int? = null
) : Parcelable