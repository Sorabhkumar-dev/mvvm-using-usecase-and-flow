package com.truely.truelymart.domain.usecases

import com.truely.truelymart.data.model.User
import com.truely.truelymart.data.repo.StoreRepository
import javax.inject.Inject

class GetTestimonialDetailUseCase @Inject constructor(private val storeRepository: StoreRepository) :
    BaseUseCase<String, User>() {
    override suspend fun getData(params: String?) = storeRepository.getTestimonialDetail(params!!)
}