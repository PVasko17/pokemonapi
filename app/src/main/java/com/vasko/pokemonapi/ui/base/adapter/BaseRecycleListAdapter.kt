package com.vasko.pokemonapi.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

abstract class BaseRecycleListAdapter<H : BaseViewHolder<E>, E> : RecyclerView.Adapter<BaseViewHolder<*>>, BaseListItems<E> {
    private var isLoading = true
    protected var isNoData = false

    /**
     * List items to display in adapter
     */
    override var items: MutableList<E>? = null
        set(value) {
            if (field == null)
                field = ArrayList()
            else
                field!!.clear()

            if (field != null && value != null)
                field?.addAll(value)

            isLoading = false
            notifyDataSetChanged()
        }

    /**
     * Error message to display on any kind of issues while populating adapter
     */
    var errorMessage = ""
        set(value) {
            field = value
            isLoading = false
            isNoData = true
            notifyDataSetChanged()
        }

    /**
     * Default constructor
     */
    constructor()

    /**
     * Initialise adapter items list along with adapter object
     *
     * @param items List to initialise an adapter
     */
    constructor(items: MutableList<E>) {
        this.items = items
    }

    /**
     * Add item to adapter and update layout
     *
     * @param e New item object to append
     */
    fun addItem(e: E) {
//        Initialise items object if trying to add first list item
        if (items == null) {
            items = ArrayList()
            isLoading = false
        }

        items!!.add(e)
        notifyItemInserted(items!!.size)
    }

    /**
     * Add item to specific position of the adapter and update layout
     *
     * @param e New item to set on specified position
     * @param position Array index to insert in
     */
    fun addItem(e: E, position: Int) {
//        Initialising items object and adding item at the beginning if no items in adapter
        if (items == null) {
            items = ArrayList()
            isLoading = false
            items!!.add(e)
            notifyDataSetChanged()
        } else {
            items!!.add(position, e)
//            Updating whole list layout if first item added to list
            if (items!!.size == 1)
                notifyDataSetChanged()
//            Updating single list View otherwise
            else
                notifyItemInserted(position)
        }
    }

    /**
     * Append array of the new items to adapter
     *
     * @param newItems List of items to append
     */
    fun addItems(newItems: ArrayList<E>) {
        val lastItem = items?.size ?: 0
        items?.addAll(newItems)
//        Notifying adapter to refresh part of the layout with the new data
        notifyItemRangeInserted(lastItem, newItems.size)
    }

    /**
     * Drop all existing items to clear the list
     */
    fun clearAllItem() {
        if (items != null) {
            items?.clear()
            isLoading = false
            notifyDataSetChanged()
        }
    }

    final override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType != VIEW_TYPE_NO_DATA && itemViewType != VIEW_TYPE_LOADING) {
            val item = getItem(position)
            // actual view
            onBindDataHolder(holder as H, position, item)
        }

        if (itemViewType == VIEW_TYPE_NO_DATA && holder is NoDataHolder) {

            if (errorMessage.isNotBlank()) {
                holder.errorTextView?.visibility = View.VISIBLE
                holder.setErrorText(errorMessage)
            } else holder.errorTextView?.visibility = View.GONE
        }
    }

    /**
     * Binds default view holder object to the list items
     */
    abstract fun onBindDataHolder(holder: H, position: Int, item: E)

    /**
     * Create item view holder
     *
     * @param parent Parent [ViewGroup] object
     * @param viewType Type that should be associated with this view
     *
     * @return View holder object
     */
    abstract fun createDataHolder(parent: ViewGroup, viewType: Int): H

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<E> {

        val baseHolder: BaseViewHolder<E> = createDataHolder(parent, viewType)

        baseHolder.listItems = this

        return baseHolder
    }

    /**
     * Find list item in specified position
     */
    override fun getItem(index: Int): E {

        if (isLoading && items?.size == index) {
            var i = index - 1
            i = if (i < 0) 0 else i
            return items!![i]
        }

        return items!![index]
    }

    override fun getItemCount(): Int {
        isNoData = items == null || items!!.isEmpty()
        return when {
            isNoData -> 1
            isLoading -> items!!.size
            else -> items!!.size
        }
    }

    /**
     * Returns item type of the specified list item
     *
     * @param position Item position
     *
     * @return Item view type
     */
    override fun getItemViewType(position: Int): Int {

        if (isNoData && isLoading)
            return VIEW_TYPE_LOADING
        else if (!isLoading && isNoData)
            return VIEW_TYPE_NO_DATA
        else if (isLoading && items!!.size == position)
            return VIEW_TYPE_LOADING

        return getViewType(position)
    }

    /**
     * Wrapper function to return default item view type
     *
     * @return [VIEW_TYPE_DATA] constant value
     */
    open fun getViewType(position: Int): Int {
        return VIEW_TYPE_DATA
    }

    /**
     * Wrapper function to inflate list item layout from resource file
     *
     * @param parent Parent [ViewGroup] object
     * @param layout Layout resource ID
     *
     * @return Inflated [View] from specified layout file
     */
    protected fun makeView(parent: ViewGroup, @LayoutRes layout: Int): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    companion object {
        const val VIEW_TYPE_NO_DATA = 1
        const val VIEW_TYPE_DATA = 2
        const val VIEW_TYPE_LOADING = 3
    }
}