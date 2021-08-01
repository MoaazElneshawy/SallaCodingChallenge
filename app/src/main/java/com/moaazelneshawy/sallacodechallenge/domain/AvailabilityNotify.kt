package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class AvailabilityNotify(
    @SerializedName("email")
    val email: Boolean,
    @SerializedName("mobile")
    val mobile: Boolean,
    @SerializedName("sms")
    val sms: Boolean
)