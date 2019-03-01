package com.sumit.learning.epaylaterassesment.activity.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.sumit.learning.epaylaterassesment.activity.base.BaseViewModel
import com.sumit.learning.epaylaterassesment.app.AppConstants
import com.sumit.learning.epaylaterassesment.model.Transaction
import com.sumit.learning.epaylaterassesment.utils.navigation.Navigation
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel(context: Application) : BaseViewModel(context) {

    val balance = ObservableField<String>()
    val waitingDialogMsg = MutableLiveData<String>()
    var isNoData = ObservableBoolean(false)
    val transactionList = MutableLiveData<List<Transaction>>()

    init {
        waitingDialogMsg.value = "Getting Balance"
        addDisposable(getApi().getBalance(AppConstants.BALANCE_NAME, AppConstants.COLLECTION_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                waitingDialogMsg.value = null
                balance.set(it?.balance+" "+it.currency)
                loadTransactions()
            },{
                waitingDialogMsg.value = null
                navigation.navigateTo(Navigation(Navigation.Screen.Error).addParam(Navigation.Param.Error, it.message))
            }))

    }

    private fun loadTransactions(){
        waitingDialogMsg.value = "Getting Recent Transactions"
        addDisposable(getApi().getTransactions(AppConstants.TRANSACTION_NAME, AppConstants.COLLECTION_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({jsonArray ->
                waitingDialogMsg.value = null
                val list = ArrayList<Transaction>()
                for (i in 0 until jsonArray.size()){
                    val jsonObject = jsonArray.get(i).asJsonObject
                    list.add(Transaction(jsonObject.get("id").asInt, jsonObject.get("date").asString,
                        jsonObject.get("description").asString,
                        jsonObject.get("amount").asString, jsonObject.get("currency").asString))
                }
                updateItems(list)
            },{
                waitingDialogMsg.value = null
                Log.e("MainViewModel","${it.printStackTrace()}")
                navigation.navigateTo(Navigation(Navigation.Screen.Error).addParam(Navigation.Param.Error, it.message))
            }))
    }

    private fun updateItems(items: List<Transaction>?){
        if (items == null || items.isEmpty()){
            isNoData.set(true)
        }else{
            isNoData.set(false)
            transactionList.value = items
        }
    }
}