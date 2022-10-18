package com.truely.truelymart.data.retrofit

import com.truely.truelymart.data.model.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface NodeApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}