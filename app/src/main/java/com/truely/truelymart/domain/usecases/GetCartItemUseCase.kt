package com.truely.truelymart.domain.usecases

import com.truely.truelymart.data.model.Cart
import com.truely.truelymart.data.repo.StoreRepository
import javax.inject.Inject

class GetCartItemUseCase @Inject constructor(private val storeRepository: StoreRepository) :
    BaseUseCase<Unit, List<Cart>>() {
    override suspend fun getData(params: Unit?) = storeRepository.getCartItems()
}