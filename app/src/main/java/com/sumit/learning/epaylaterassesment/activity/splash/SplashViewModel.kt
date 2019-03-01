package com.sumit.learning.epaylaterassesment.activity.splash

import android.app.Application
import com.sumit.learning.epaylaterassesment.activity.base.BaseViewModel
import com.sumit.learning.epaylaterassesment.app.AppConstants
import com.sumit.learning.epaylaterassesment.utils.navigation.Navigation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashViewModel(context: Application) : BaseViewModel(context){

    init {
        addDisposable(
            Observable.timer(AppConstants.SPLASH_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loginUser()
                }
        )
    }

    private fun loginUser(){
        addDisposable(getApi().loginUser(AppConstants.LOGIN_ID, AppConstants.LOGIN_NAME, AppConstants.COLLECTION_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getPref().setLoginToken(it.token)
                navigateToHome()
            },{
                navigation.navigateTo(Navigation(Navigation.Screen.Error).addParam(Navigation.Param.Error, it.message))
            }))
    }

    private fun navigateToHome(){
        navigation.navigateTo(Navigation.Screen.Home)
    }
}