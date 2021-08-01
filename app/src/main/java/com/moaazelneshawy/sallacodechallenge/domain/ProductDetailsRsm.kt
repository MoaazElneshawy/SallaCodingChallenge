package com.moaazelneshawy.sallacodechallenge.domain


import com.google.gson.annotations.SerializedName

data class ProductDetailsRsm(
    @SerializedName("brand")
    val brand: Brand,
    @SerializedName("cost_price")
    val costPrice: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("favorite")
    val favorite: Any,
    @SerializedName("features")
    val features: Features,
    @SerializedName("has_special_price")
    val hasSpecialPrice: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("included_tax")
    val includedTax: Boolean,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("managed_by_branches")
    val managedByBranches: Boolean,
    @SerializedName("max_items_per_user")
    val maxItemsPerUser: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("notify_quantity")
    val notifyQuantity: Any,
    @SerializedName("options")
    val options: List<Any>,
    @SerializedName("pre_tax_price")
    val preTaxPrice: PreTaxPrice,
    @SerializedName("price")
    val price: Price,
    @SerializedName("promotion")
    val promotion: Promotion,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("regular_price")
    val regularPrice: RegularPrice,
    @SerializedName("require_shipping")
    val requireShipping: Boolean,
    @SerializedName("sale_end")
    val saleEnd: String,
    @SerializedName("sale_price")
    val salePrice: SalePrice,
    @SerializedName("short_link_code")
    val shortLinkCode: String,
    @SerializedName("show_in_app")
    val showInApp: Boolean,
    @SerializedName("show_purchase_count")
    val showPurchaseCount: Boolean,
    @SerializedName("sku")
    val sku: Any,
    @SerializedName("skus")
    val skus: List<Any>,
    @SerializedName("sold_quantity")
    val soldQuantity: String,
    @SerializedName("sold_quantity_desc")
    val soldQuantityDesc: String,
    @SerializedName("starting_price")
    val startingPrice: Any,
    @SerializedName("status")
    val status: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("tax")
    val tax: Tax,
    @SerializedName("taxed_price")
    val taxedPrice: TaxedPrice,
    @SerializedName("type")
    val type: String,
    @SerializedName("unlimited_quantity")
    val unlimitedQuantity: Boolean,
    @SerializedName("url")
    val url: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("views")
    val views: Any,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("with_tax")
    val withTax: Boolean
)