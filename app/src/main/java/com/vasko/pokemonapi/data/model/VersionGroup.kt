package com.vasko.pokemonapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VersionGroup(
    @field:SerializedName("level_learned_at") val levelLearnedAt: Int,
    @field:SerializedName("move_learn_method") val moveLearnMethod: BaseItem,
    @field:SerializedName("version_group") val versionGroup: BaseItem
): Parcelable