package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Move(
    @field:SerializedName("move") val move: BaseItem,
    @field:SerializedName("version_group_details") val versionGroupDetails: ArrayList<VersionGroup>
) : Parcelable