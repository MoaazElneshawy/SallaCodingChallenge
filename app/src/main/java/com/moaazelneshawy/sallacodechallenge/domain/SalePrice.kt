package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class SalePrice(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("currency")
    val currency: String
)