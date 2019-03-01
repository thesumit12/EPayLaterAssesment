package com.sumit.learning.epaylaterassesment.app

import android.content.SharedPreferences

private const val PREF_TOKEN = "pref.token"

class AppPreferences(private val sharedPreferences: SharedPreferences) {

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getString(PREF_TOKEN,null)!=null
    }

    fun setLoginToken(token: String?){
        saveString(PREF_TOKEN, token)
    }

    fun getLoginToken(): String? = sharedPreferences.getString(PREF_TOKEN, null)

    private fun saveString(pref: String, value: String?) {
        sharedPreferences.edit().apply{
            if (value==null)
                remove(pref)
            else
                putString(pref,value)
        }.apply()

    }

    fun clearPref(){
        sharedPreferences.edit().clear().apply()
    }
}