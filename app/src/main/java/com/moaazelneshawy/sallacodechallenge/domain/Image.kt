package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("video_url")
    val videoUrl: String
)