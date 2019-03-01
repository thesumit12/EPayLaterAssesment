package com.sumit.learning.epaylaterassesment.utils.navigation

import java.util.HashMap

class Navigation(val fromScreen: Screen?, val screen: Screen) {

    constructor(screen: Screen):this(null,screen)

    enum class Screen{
        None,
        Home,
        Back,
        Error,
        Transaction
    }

    enum class Param(val value: String){
        Token("token"),
        Error("error")
    }

    private val params:MutableMap<String, Any?> = HashMap()

    fun addParam(name: Param, value:Any?): Navigation {
        params[name.value] = value
        return this
    }
    fun getParam(name: Param):Any?{
        return params[name.value]
    }
    companion object {
        val None = Navigation(Screen.None)
    }
}