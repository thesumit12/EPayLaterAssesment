package com.sumit.learning.epaylaterassesment.activity.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.sumit.learning.epaylaterassesment.R
import com.sumit.learning.epaylaterassesment.utils.WaitingDialogHelper



@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private val waitingDialog = WaitingDialogHelper()

    fun showWaitingDialog(msg: Int){
        waitingDialog.show(this, msg)
    }

    fun showWaitingDialog(msg: String){
        waitingDialog.show(this, msg)
    }

    fun hideWaitingDialog(){
        waitingDialog.hideDialog()
    }
}