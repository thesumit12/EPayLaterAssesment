package com.sumit.learning.epaylaterassesment.network

import android.util.Log
import com.sumit.learning.epaylaterassesment.BuildConfig
import com.sumit.learning.epaylaterassesment.app.AppPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(private  val appPreferences: AppPreferences) {

    private var RETROFIT_INSTANCE: Retrofit? = null

    fun getClient(): Retrofit{
        if (RETROFIT_INSTANCE == null){
            RETROFIT_INSTANCE = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return RETROFIT_INSTANCE!!
    }

    private fun createHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.SECONDS)
            .addInterceptor(provideInterceptor())
            .addInterceptor(provideAuthInterceptor())
            .build()
    }



    private fun provideInterceptor():HttpLoggingInterceptor{
        val interceptor =  HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY;
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE;
        }

        return interceptor
    }

    private fun provideAuthInterceptor(): Interceptor{
        return Interceptor { chain ->
            val original = chain.request()
            val response: Response

            response = if (appPreferences.isUserLoggedIn()){
                val request =original.newBuilder()
                    .header("Authorization", "Bearer ${appPreferences.getLoginToken()}")
                    .build()
                chain.proceed(request)
            }else{
                Log.e("ApiClient","User not logged in")
                chain.proceed(original)
            }

            response
        }
    }

}