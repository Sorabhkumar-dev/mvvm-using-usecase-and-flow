package com.truely.truelymart.data.repo

import com.truely.truelymart.data.model.Product
import com.truely.truelymart.data.model.ProductInfo
import com.truely.truelymart.data.model.User
import com.truely.truelymart.data.retrofit.NodeApiInterface
import com.truely.truelymart.data.retrofit.Result
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val nodeApiInterface: NodeApiInterface) :StoreRepository{
    override suspend fun getProducts(): Result<List<Product>> {
        val response = nodeApiInterface.getProducts()
        return try {
          return  if (response.isSuccessful)
            Result.Success(response.body()!!,response.code(),response.message())
            else
                Result.Error(response.code(),response.message())
        }catch (e:Exception){
            Result.Error(response.code(),e.message!!)
        }
    }

    override suspend fun getProductInfo(productId: String): Result<ProductInfo> {
        val response = nodeApiInterface.getProductInfo(productId)
        return try {
            return if (response.isSuccessful)
                Result.Success(response.body(), response.code(), response.message())
            else
                Result.Error(response.code(), response.message())
        } catch (e: Exception) {
            Result.Error(response.code(), e.message)
        }
    }

    override suspend fun getUsers(): Result<List<User>> {
        val response = nodeApiInterface.getUsers()
        return try {
            return if (response.isSuccessful)
                Result.Success(response.body(), response.code(), response.message())
            else
                Result.Error(response.code(), response.message())
        } catch (e: Exception) {
            Result.Error(response.code(), response.message())
        }
    }

}