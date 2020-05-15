package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Type(
    @field:SerializedName("slot") val slot: Int,
    @field:SerializedName("type") val type: BaseItem
): Parcelable