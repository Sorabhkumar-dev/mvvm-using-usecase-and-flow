package com.truely.truelymart.data.retrofit

import com.truely.truelymart.data.model.Cart
import com.truely.truelymart.data.model.Product
import com.truely.truelymart.data.model.ProductInfo
import com.truely.truelymart.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NodeApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("products/{productId}")
    suspend fun getProductInfo(@Path("productId")productId:String):Response<ProductInfo>

    @GET("users")
    suspend fun getUsers():Response<List<User>>

    @GET("users/{testimonialId}")
    suspend fun getTestimonialDetail(@Path("testimonialId")testimonialId:String):Response<User>

    @GET("carts")
    suspend fun getCartItems():Response<List<Cart>>
}