package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class Cursor(
    @SerializedName("count")
    val count: Int,
    @SerializedName("current")
    val current: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any
)