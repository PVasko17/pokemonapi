package com.vasko.pokemonapi.ui.base.adapter

import android.view.View

interface OnRecycleItemClick<T> {
    fun onClick(t: T, view: View)
}