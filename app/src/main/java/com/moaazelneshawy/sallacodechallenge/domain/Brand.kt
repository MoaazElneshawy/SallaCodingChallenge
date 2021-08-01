package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("ar_char")
    val arChar: String,
    @SerializedName("banner")
    val banner: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("en_char")
    val enChar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String
)