package com.truely.truelymart.data.di

import com.truely.truelymart.data.repo.StoreRepository
import com.truely.truelymart.data.repo.StoreRepositoryImpl
import com.truely.truelymart.data.retrofit.NodeApiClient
import com.truely.truelymart.data.retrofit.NodeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {
    @Provides
    @Singleton
    fun provideNodeApiInterface():NodeApiInterface = NodeApiClient.nodeApiInterface

    @Provides
    @Singleton
    fun provideStoreRepository(storeRepository: StoreRepositoryImpl):StoreRepository = storeRepository

}