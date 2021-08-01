package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class BrandProduct(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("favorite")
    val favorite: Any,
    @SerializedName("has_special_price")
    val hasSpecialPrice: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("promotion")
    val promotion: Promotion,
    @SerializedName("regular_price")
    val regularPrice: RegularPrice,
    @SerializedName("sale_price")
    val salePrice: SalePrice,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("starting_price")
    val startingPrice: Any,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)