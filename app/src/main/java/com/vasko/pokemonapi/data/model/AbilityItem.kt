package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AbilityItem(
    @field:SerializedName("is_hidden") val isHidden: Boolean,
    @field:SerializedName("slot") val slot: Int,
    @field:SerializedName("ability") val ability: BaseItem
) : Parcelable