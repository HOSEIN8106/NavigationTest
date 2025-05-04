package com.example.navigationtest.hilt.module

import com.example.navigationtest.data.remote.api.DirectionApiService
import com.example.navigationtest.data.remote.repository.DirectionRepositoryImpl
import com.example.navigationtest.domain.repository.DirectionRepository
import com.example.navigationtest.domain.usecase.GetDirectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDirectionApi(): DirectionApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionApiService::class.java)
    }

    @Provides
    fun provideDirectionRepository(api: DirectionApiService): DirectionRepository =
        DirectionRepositoryImpl(api)

    @Provides
    fun provideGetDirectionsUseCase(repository: DirectionRepository): GetDirectionsUseCase =
        GetDirectionsUseCase(repository)
}