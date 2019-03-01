package com.sumit.learning.epaylaterassesment.activity.transaction

import android.app.Application
import android.databinding.ObservableField
import android.text.TextUtils
import com.sumit.learning.epaylaterassesment.R
import com.sumit.learning.epaylaterassesment.activity.base.BaseViewModel

class CreateTransactionViewModel(context: Application) : BaseViewModel(context) {

    val amount = ObservableField<String>()
    val currency = ObservableField<String>()
    val date = ObservableField<String>()
    val description = ObservableField<String>()

    val errorAmount = ObservableField<Int>()
    val errorDescription = ObservableField<Int>()

    fun createTransaction(){
        resetError()
        if (validateFields()){

        }
    }

    private fun validateFields(): Boolean{
        return when {
            TextUtils.isEmpty(amount.get()) -> {
                errorAmount.set(R.string.empty_error)
                false
            }
            TextUtils.isEmpty(description.get()) -> {
                errorDescription.set(R.string.empty_error)
                false
            }
            else -> true
        }
    }

    private fun resetError(){
        errorAmount.set(null)
        errorDescription.set(null)
    }
}