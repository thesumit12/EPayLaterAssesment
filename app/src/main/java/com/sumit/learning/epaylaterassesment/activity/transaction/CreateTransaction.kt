package com.sumit.learning.epaylaterassesment.activity.transaction

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.sumit.learning.epaylaterassesment.R
import com.sumit.learning.epaylaterassesment.activity.base.BaseActivity
import com.sumit.learning.epaylaterassesment.databinding.ActivityCreateTransactionBinding

class CreateTransaction : BaseActivity(), ClickHandler {

    private lateinit var viewModel: CreateTransactionViewModel

    private lateinit var binding: ActivityCreateTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_transaction)
        viewModel = ViewModelProviders.of(this).get(CreateTransactionViewModel::class.java)
        binding.clickHandler = this
        binding.viewModel = viewModel
    }

    override fun createTransaction() {
        viewModel.createTransaction()
    }

    companion object {

        fun show(context: Context){
            val intent = getIntent(context)
            ActivityCompat.startActivity(context, intent, null)
        }

        private fun getIntent(context: Context): Intent {
            return Intent(context, CreateTransaction::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}
