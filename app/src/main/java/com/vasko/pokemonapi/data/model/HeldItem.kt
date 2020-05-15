package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeldItem(
    @field:SerializedName("item") val item: BaseItem,
    @field:SerializedName("version_details") val versionDetails: ArrayList<Version>
): Parcelable