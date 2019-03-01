package com.sumit.learning.epaylaterassesment.activity.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.sumit.learning.epaylaterassesment.app.AppPreferences
import com.sumit.learning.epaylaterassesment.network.Api
import com.sumit.learning.epaylaterassesment.network.ApiClient
import com.sumit.learning.epaylaterassesment.network.ApiInterface
import com.sumit.learning.epaylaterassesment.utils.navigation.NavigationLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(private val context: Application) : AndroidViewModel(context) {

    private val disposables = CompositeDisposable()
    val navigation = NavigationLiveData()

    private var api: Api? = null
    private var appPreferences: AppPreferences? = null

    protected fun addDisposable(d: Disposable){
        disposables.add(d)
    }
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun getApi(): Api{
        if (api == null){
            val apiClient = ApiClient(getPref())
            val apiInterface = apiClient.getClient().create(ApiInterface::class.java)
            api = Api(context, apiInterface, Gson())
        }
        return api!!
    }

    fun getPref(): AppPreferences{
        if (appPreferences == null){
            appPreferences = AppPreferences(PreferenceManager.getDefaultSharedPreferences(context))
        }

        return appPreferences!!
    }

}