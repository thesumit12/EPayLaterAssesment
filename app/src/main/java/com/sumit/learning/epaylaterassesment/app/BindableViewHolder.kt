package com.sumit.learning.epaylaterassesment.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 */
abstract class BindableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position:Int)
    fun getContext(): Context{
        return itemView.context
    }

    protected fun <T> getClickedItem(items: List<T>?): T? {
        return if (adapterPosition==-1 || items==null || items.size<adapterPosition+1)
            null
        else
            items[adapterPosition]
    }
}