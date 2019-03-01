package com.sumit.learning.epaylaterassesment.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.sumit.learning.epaylaterassesment.R


class WaitingDialogHelper {
    private var dlg: AlertDialog? = null
    private val isShowing: Boolean
        get() = dlg?.isShowing == true

    fun show(context: Context, title: Int) {
        hideDialog()
        val body = LayoutInflater.from(context).inflate(R.layout.waiting_large, null, false)
        val builder = AlertDialog.Builder(context)
                .setTitle(title)
                .setView(body)
                .setCancelable(true)
        dlg = builder.show()
    }

    fun show(context: Context, title: String) {
        hideDialog()
        val body = LayoutInflater.from(context).inflate(R.layout.waiting_large, null, false)
        val builder = AlertDialog.Builder(context)
                .setTitle(title)
                .setView(body)
                .setCancelable(true)
        dlg = builder.show()
    }

    fun hideDialog() {
        if (isShowing) {
            try {
                dlg?.dismiss()
            } catch (ignore: Exception) {
            }

            dlg = null
        }
    }

}
