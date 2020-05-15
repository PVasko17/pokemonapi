package com.vasko.pokemonapi.data.model

import android.os.Parcelable

interface IBaseItem: Parcelable {
    val name: String
    val url: String
}