package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class Features(
    @SerializedName("availability_notify")
    val availabilityNotify: AvailabilityNotify,
    @SerializedName("show_rating")
    val showRating: Boolean,
    @SerializedName("show_remaining_quantity")
    val showRemainingQuantity: Boolean,
    @SerializedName("show_you_may_like")
    val showYouMayLike: Boolean
)