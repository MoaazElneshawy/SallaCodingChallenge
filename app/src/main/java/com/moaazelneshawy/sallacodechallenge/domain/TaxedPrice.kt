package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class TaxedPrice(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("currency")
    val currency: String
)