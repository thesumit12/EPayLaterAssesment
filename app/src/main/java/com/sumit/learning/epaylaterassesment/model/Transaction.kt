package com.sumit.learning.epaylaterassesment.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("amount")
    val amount: String,

    @field:SerializedName("currency")
    val currency: String
)