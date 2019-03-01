package com.sumit.learning.epaylaterassesment.utils

import android.support.v7.util.DiffUtil
import com.sumit.learning.epaylaterassesment.model.Transaction

class TransactionDiffCallback(private val oldItems: List<Transaction>, private val newItems: List<Transaction>)
    :DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }
}