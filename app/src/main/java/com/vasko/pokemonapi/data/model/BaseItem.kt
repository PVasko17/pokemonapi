package com.vasko.pokemonapi.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseItem(
    @field:SerializedName("name") override val name: String,
    @field:SerializedName("url") override val url: String
) : IBaseItem