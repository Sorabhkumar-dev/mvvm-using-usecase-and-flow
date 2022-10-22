package com.truely.truelymart.domain.usecases

import com.truely.truelymart.data.model.User
import com.truely.truelymart.data.repo.StoreRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val storeRepository: StoreRepository):BaseUseCase<Unit,List<User>>() {
    override suspend fun getData(params: Unit?) = storeRepository.getUsers()
}