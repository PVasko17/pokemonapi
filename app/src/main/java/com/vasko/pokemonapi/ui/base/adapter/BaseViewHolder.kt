package com.vasko.pokemonapi.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<E> : RecyclerView.ViewHolder, View.OnClickListener {
    private var onRecycleItemClick: OnRecycleItemClick<E>? = null
    var listItems: BaseListItems<E>? = null

    val current: E
        get() = listItems!!.getItem(layoutPosition)

    constructor(itemView: View) : super(itemView) {
        itemView.setOnClickListener(this)
    }

    constructor(itemView: View, onRecycleItemClick: OnRecycleItemClick<E>) : super(itemView) {

        this.onRecycleItemClick = onRecycleItemClick
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        onRecycleItemClick?.onClick(listItems!!.getItem(layoutPosition), v)
    }

}