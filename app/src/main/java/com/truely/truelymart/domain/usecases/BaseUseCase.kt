package com.truely.truelymart.domain.usecases

import com.truely.truelymart.data.retrofit.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<in P, R> {
    private suspend fun getResponse(params: P?): Flow<Result<R>> = flow {
        emit(Result.Loading())
        try {
            emit(getData(params))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message.toString()))
        }
    }

    abstract suspend fun getData(params: P?): Result<R>

    suspend operator fun invoke(params: P) = getResponse(params)

    suspend operator fun invoke() = getResponse(null)
}