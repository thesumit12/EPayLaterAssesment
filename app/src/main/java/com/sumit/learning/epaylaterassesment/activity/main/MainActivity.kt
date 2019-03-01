package com.sumit.learning.epaylaterassesment.activity.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import com.sumit.learning.epaylaterassesment.R
import com.sumit.learning.epaylaterassesment.activity.base.BaseActivity
import com.sumit.learning.epaylaterassesment.activity.main.adapter.TransactionAdapter
import com.sumit.learning.epaylaterassesment.activity.transaction.CreateTransaction
import com.sumit.learning.epaylaterassesment.databinding.ActivityMainBinding
import com.sumit.learning.epaylaterassesment.utils.Notif
import com.sumit.learning.epaylaterassesment.utils.navigation.Navigation

class MainActivity : BaseActivity(), ClickHandler {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private val adapter = TransactionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.clickHandler = this

        setUpAdapter()
        subscribeObservers()
    }

    private fun setUpAdapter(){
        binding.rvTransaction.layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = adapter
    }

    private fun subscribeObservers(){
        viewModel.navigation.observe(this, Observer {
            when{
                it!!.screen == Navigation.Screen.Error -> navigateToError(it.getParam(Navigation.Param.Error) as String?)
            }
        })

        viewModel.transactionList.observe(this, Observer {
            adapter.update(it)
        })

        viewModel.waitingDialogMsg.observe(this, Observer {
            msg ->
            if (msg != null){
                showWaitingDialog(msg)
            }else{
                hideWaitingDialog()
            }
        })
    }

    private fun navigateToError(errorMsg: String?){
        viewModel.navigation.navigated()
        if (errorMsg != null){
            Notif.alertAppClose(this, R.string.error_title, errorMsg)
        }
    }

    override fun createTransactionClicked() {
        viewModel.navigation.navigated()
        CreateTransaction.show(this)
    }

    companion object {

        fun show(context: Context){
            val intent = getIntent(context)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            ActivityCompat.startActivity(context, intent, null)
        }

        private fun getIntent(context: Context): Intent{
            return Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}
