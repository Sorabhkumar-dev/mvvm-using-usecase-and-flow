package com.truely.truelymart.domain.usecases

import com.truely.truelymart.data.model.ProductInfo
import com.truely.truelymart.data.repo.StoreRepository
import javax.inject.Inject

class GetProductInfoUseCase @Inject constructor(private val storeRepository: StoreRepository):BaseUseCase<String,ProductInfo>() {
    override suspend fun getData(params: String?) = storeRepository.getProductInfo(params!!)
}