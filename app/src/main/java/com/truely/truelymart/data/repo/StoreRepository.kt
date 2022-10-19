package com.truely.truelymart.data.repo

import com.truely.truelymart.data.model.Product
import com.truely.truelymart.data.model.ProductInfo
import com.truely.truelymart.data.retrofit.Result

interface StoreRepository {
    suspend fun getProducts():Result<List<Product>>

    suspend fun getProductInfo(productId:String): Result<ProductInfo>
}