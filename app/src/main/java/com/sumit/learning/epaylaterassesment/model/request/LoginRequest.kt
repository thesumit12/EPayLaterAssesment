package com.sumit.learning.epaylaterassesment.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("collectionId")
    val collectionId: String

)