package com.vasko.pokemonapi.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.vasko.pokemonapi.R

/**
 * Extension function to load images to ImageView with placeholder using Glide
 */
fun AppCompatImageView.load(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .into(this)
}
