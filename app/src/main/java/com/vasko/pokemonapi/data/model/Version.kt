package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Version(
    @field:SerializedName("rarity") val rarity: Int,
    @field:SerializedName("version") val version: BaseItem
): Parcelable