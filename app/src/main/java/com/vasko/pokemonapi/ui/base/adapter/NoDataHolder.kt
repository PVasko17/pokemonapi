package com.vasko.pokemonapi.ui.base.adapter

import android.view.View
import android.widget.TextView

class NoDataHolder<E>(itemView: View) : BaseViewHolder<E>(itemView) {
    var errorTextView: TextView? = null

    init {
        errorTextView = itemView as TextView
    }

    fun setErrorText(errorText: String) {
        if (errorTextView != null) {

            if (errorText.isNotEmpty()) {
                errorTextView!!.visibility = View.VISIBLE
                errorTextView!!.text = errorText
            } else errorTextView!!.visibility = View.GONE

        }
    }
}