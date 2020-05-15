package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stat(
    @field:SerializedName("base_stat") val baseStat: Int,
    @field:SerializedName("effort") val effort: Int,
    @field:SerializedName("stat") val stat: BaseItem
): Parcelable