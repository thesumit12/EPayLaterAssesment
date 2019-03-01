package com.sumit.learning.epaylaterassesment.utils

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.Toast

class Notif {

    interface OnButtonClickListener {
        fun onButtonClick(id: Int)
    }

    companion object {

        fun toast(context: Context?, msg: Int) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun toast(context: Context?, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun ask(context: Context?, title: Int, msg: Int, buttonNegative: Int, buttonPositive: Int, buttonNeutral: Int, clickListener: OnButtonClickListener) {
            if (context == null)
                return
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            if (buttonPositive > 0)
                builder.setPositiveButton(buttonPositive) { _, _ -> clickListener.onButtonClick(buttonPositive) }
            if (buttonNegative > 0)
                builder.setNegativeButton(buttonNegative) { _, _ -> clickListener.onButtonClick(buttonNegative) }
            if (buttonNeutral > 0)
                builder.setNeutralButton(buttonNeutral) { _, _ -> clickListener.onButtonClick(buttonNeutral) }
            builder.setOnCancelListener { _ -> clickListener.onButtonClick(buttonNegative) }

            builder.show()
        }

        fun ask(context: Context?, title: Int, msg: String,
                buttonNegative: Int, buttonPositive: Int, buttonNeutral: Int, clickListener: OnButtonClickListener) {
            if (context == null)
                return
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            if (buttonPositive > 0)
                builder.setPositiveButton(buttonPositive) { _, _ -> clickListener.onButtonClick(buttonPositive) }
            if (buttonNegative > 0)
                builder.setNegativeButton(buttonNegative) { _, _ -> clickListener.onButtonClick(buttonNegative) }
            if (buttonNeutral > 0)
                builder.setNeutralButton(buttonNeutral) { _, _ -> clickListener.onButtonClick(buttonNeutral) }
            builder.setOnCancelListener { _ -> clickListener.onButtonClick(buttonNegative) }

            builder.show()
        }



        fun ask(context: Context?, title: Int, msg: String,
                buttonNegative: Int, buttonPositive: Int, buttonNeutral: Int, clickListener: OnButtonClickListener
                ,isCancelable: Boolean) {
            if (context == null)
                return
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            builder.setCancelable(isCancelable)
            if (buttonPositive > 0)
                builder.setPositiveButton(buttonPositive) { _, _ -> clickListener.onButtonClick(buttonPositive) }
            if (buttonNegative > 0)
                builder.setNegativeButton(buttonNegative) { _, _ -> clickListener.onButtonClick(buttonNegative) }
            if (buttonNeutral > 0)
                builder.setNeutralButton(buttonNeutral) { _, _ -> clickListener.onButtonClick(buttonNeutral) }
            builder.setOnCancelListener { _ -> clickListener.onButtonClick(buttonNegative) }

            builder.show()
        }



        fun ask(context: Context?, title: Int, msg: Int, buttonNegative: Int, buttonPositive: Int, clickListener: OnButtonClickListener) {
            ask(context, title, msg, buttonNegative, buttonPositive, 0, clickListener)
        }
        fun alert(context: Context?, title: Int, msg: Int){
            ask(context,title,msg, android.R.string.ok,0,0,object: OnButtonClickListener {
                override fun onButtonClick(id: Int) {
                    //ignore
                }
            })
        }
        fun alert(context: Context?, title: Int, msg: String){
            ask(context,title,msg, android.R.string.ok,0,0,object: OnButtonClickListener {
                override fun onButtonClick(id: Int) {
                    //ignore
                }
            })
        }

        fun alertAppClose(context: Activity, title: Int, msg: String){
            ask(context,title,msg, android.R.string.ok,0,0,object: OnButtonClickListener {
                override fun onButtonClick(id: Int) {

                    context.finish()
                }
            })
        }
    }
}