package com.sumit.learning.epaylaterassesment.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.sumit.learning.epaylaterassesment.model.request.LoginRequest
import com.sumit.learning.epaylaterassesment.model.response.BalanceResponse
import com.sumit.learning.epaylaterassesment.model.response.LoginResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

class Api(
    private val context: Context,
    private val apiService: ApiInterface,
    private val gson: Gson
    ) {


    fun loginUser(id: String, name:String, collectionId: String): Observable<LoginResponse>{
        return InternetConnection.isInternetOn(context)
            .switchMap<LoginResponse> { connectionStatus ->
                if (connectionStatus){
                    return@switchMap apiService.loginUser(
                        getJsonBody(LoginRequest(id, name, collectionId))
                    ).subscribeOn(Schedulers.io())
                }else {
                    throw InternetConnection.NoNetworkException()
                }
            }
    }

    fun getBalance(name: String, collectionId: String): Observable<BalanceResponse>{
        return InternetConnection.isInternetOn(context)
            .switchMap<BalanceResponse> { connectionStatus ->
                if (connectionStatus){
                    return@switchMap apiService.getCurrentBalance(name, collectionId)
                    .subscribeOn(Schedulers.io())
                }else {
                    throw InternetConnection.NoNetworkException()
                }
            }
    }

    fun getTransactions(name: String, collectionId: String): Observable<JsonArray>{
        return InternetConnection.isInternetOn(context)
            .switchMap<JsonArray> { connectionStatus ->
                if (connectionStatus){
                    return@switchMap apiService.getTransactions(name, collectionId)
                        .subscribeOn(Schedulers.io())
                }else {
                    throw InternetConnection.NoNetworkException()
                }
            }
    }

    private fun getJsonBody(request: Any): RequestBody {
        return RequestBody.create(MediaType.parse("application/json"), gson.toJson(request))
    }
}