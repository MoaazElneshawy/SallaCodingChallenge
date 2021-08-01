package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("admin")
    val admin: String,
    @SerializedName("customer")
    val customer: String
)