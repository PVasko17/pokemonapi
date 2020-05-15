package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameIndex(
    @field:SerializedName("game_index") val gameIndex: Int,
    @field:SerializedName("version") val version: BaseItem
) : Parcelable