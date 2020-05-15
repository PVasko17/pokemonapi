package com.vasko.pokemonapi.ui.base.adapter

interface BaseListItems<E> {
    fun getItem(index: Int): E
    var items: MutableList<E>?
}