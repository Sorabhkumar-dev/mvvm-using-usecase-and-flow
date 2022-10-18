package com.truely.truelymart.data.repo

import com.truely.truelymart.data.model.Product
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.data.retrofit.TruelyResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface StoreRepository {
    suspend fun getProducts():Result<List<Product>>
}