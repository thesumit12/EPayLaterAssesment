package com.sumit.learning.epaylaterassesment.model.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class BalanceResponse(
    @field:SerializedName("balance")
    val balance: String? = null,

    @field:SerializedName("currency")
    val currency: String? = null
)