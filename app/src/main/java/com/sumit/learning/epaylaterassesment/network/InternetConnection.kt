package com.sumit.learning.epaylaterassesment.network

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable


/**
 * Created by Oliver on 06.10.2018.
 */
object InternetConnection {
    class NoNetworkException : RuntimeException()

    fun isInternetOn(context: Context): Observable<Boolean> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return Observable.just((connectivityManager.activeNetworkInfo == null || !connectivityManager.activeNetworkInfo.isConnected).not())
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (connectivityManager.activeNetworkInfo == null || !connectivityManager.activeNetworkInfo.isConnected).not()
    }

    override fun toString(): String {
        return "No Network"
    }
}