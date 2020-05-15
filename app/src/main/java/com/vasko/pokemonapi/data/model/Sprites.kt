package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sprites(
    @field:SerializedName("back_default") val backDefault: String,
    @field:SerializedName("back_female") val backFemale: String?,
    @field:SerializedName("back_shiny") val backShiny: String,
    @field:SerializedName("back_shiny_female") val backShinyFemale: String?,
    @field:SerializedName("front_default") val frontDefault: String,
    @field:SerializedName("front_female") val frontFemale: String?,
    @field:SerializedName("front_shiny") val frontShiny: String,
    @field:SerializedName("front_shiny_female") val frontShinyFemale: String?
): Parcelable