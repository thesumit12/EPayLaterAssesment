package com.sumit.learning.epaylaterassesment.activity.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.sumit.learning.epaylaterassesment.R
import com.sumit.learning.epaylaterassesment.app.BindableViewHolder
import com.sumit.learning.epaylaterassesment.databinding.TransactionItemBinding
import com.sumit.learning.epaylaterassesment.model.Transaction
import com.sumit.learning.epaylaterassesment.utils.TransactionDiffCallback

class TransactionAdapter() : RecyclerView.Adapter<BindableViewHolder>() {

    private val transactionList = ArrayList<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        return TransactionViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false))
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    fun update(items: List<Transaction>?){
        if (items == null || items.isEmpty()){
            transactionList.clear()
            notifyDataSetChanged()
        }else{
            val result =DiffUtil.calculateDiff(TransactionDiffCallback(this.transactionList, items))
            this.transactionList.clear()
            this.transactionList.addAll(items)
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onBindViewHolder(viewHolder: BindableViewHolder, position: Int) {
        viewHolder.bind(position)
    }

    inner class TransactionViewHolder(itemView: View) : BindableViewHolder(itemView){
        private val binding: TransactionItemBinding = DataBindingUtil.bind(itemView)!!
        override fun bind(position: Int) {
            binding.transaction = transactionList[position]
            binding.executePendingBindings()
        }
    }
}