package com.sumit.learning.epaylaterassesment.activity.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.sumit.learning.epaylaterassesment.R
import com.sumit.learning.epaylaterassesment.activity.base.BaseActivity
import com.sumit.learning.epaylaterassesment.activity.main.MainActivity
import com.sumit.learning.epaylaterassesment.utils.Notif
import com.sumit.learning.epaylaterassesment.utils.navigation.Navigation

class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.navigation.observe(this, Observer {
            when{
                it!!.screen == Navigation.Screen.Home -> navigateToMain()
                it.screen == Navigation.Screen.Error -> showError(it.getParam(Navigation.Param.Error) as String?)
            }

        })
    }

    private fun navigateToMain(){
        viewModel.navigation.navigated()
        MainActivity.show(this)
    }

    private fun showError(errorMsg: String?){
        viewModel.navigation.navigated()
        if (errorMsg != null){
            Notif.alertAppClose(this, R.string.error_title, errorMsg)
        }
    }
}
