package com.truely.truelymart.domain.usecases

import com.truely.truelymart.data.model.Product
import com.truely.truelymart.data.repo.StoreRepository
import com.truely.truelymart.data.retrofit.Result
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val storeRepository: StoreRepository):BaseUseCase<Unit,List<Product>>() {
    override suspend fun getData(params: Unit?) = storeRepository.getProducts()
}