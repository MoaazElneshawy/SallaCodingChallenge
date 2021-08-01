package com.moaazelneshawy.sallacodechallenge.data.network

import com.moaazelneshawy.sallacodechallenge.core.BaseRsm
import com.moaazelneshawy.sallacodechallenge.domain.BrandProduct
import com.moaazelneshawy.sallacodechallenge.domain.ProductDetailsRsm
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/


interface ApiService {

    @GET("brands/{id}")
    suspend fun getAllProducts(
        @Path("id") id: Int, // 259940351
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<BaseRsm<List<BrandProduct>>>

}