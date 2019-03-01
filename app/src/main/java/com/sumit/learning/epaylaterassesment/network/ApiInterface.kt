package com.sumit.learning.epaylaterassesment.network

import com.google.gson.JsonArray
import com.sumit.learning.epaylaterassesment.model.response.BalanceResponse
import com.sumit.learning.epaylaterassesment.model.response.LoginResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {

    @Headers("Accept: application/json")
    @POST("/login")
    fun loginUser(@Body requestBody: RequestBody): Observable<LoginResponse>

    @Headers("Accept: application/json")
    @GET("/balance")
    fun getCurrentBalance(@Query("name") name: String,
                          @Query("collectionId") collectionId: String): Observable<BalanceResponse>

    @Headers("Accept: application/json")
    @GET("/transactions")
    fun getTransactions(@Query("name") name: String,
                        @Query("collectionId") collectionId: String): Observable<JsonArray>


}
