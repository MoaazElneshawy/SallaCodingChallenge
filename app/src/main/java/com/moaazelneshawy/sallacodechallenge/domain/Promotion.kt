package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class Promotion(
    @SerializedName("sub_title")
    val subTitle: String,
    @SerializedName("title")
    val title: String?
)