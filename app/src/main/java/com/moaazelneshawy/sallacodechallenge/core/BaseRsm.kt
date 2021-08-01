package com.moaazelneshawy.sallacodechallenge.core


import com.google.gson.annotations.SerializedName
import com.moaazelneshawy.sallacodechallenge.domain.Brand
import com.moaazelneshawy.sallacodechallenge.domain.Cursor

data class BaseRsm<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("brand")
    val brand : Brand?,
    @SerializedName("cursor")
    val cursor : Cursor?
)