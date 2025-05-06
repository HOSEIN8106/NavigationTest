package com.example.navigationtest.hilt.module

import com.example.navigationtest.data.remote.api.DirectionApiService
import com.example.navigationtest.data.remote.repository.DirectionRepositoryImpl
import com.example.navigationtest.domain.repository.DirectionRepository
import com.example.navigationtest.domain.usecase.GetDirectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.maplibre.android.BuildConfig
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
            .client(provideOkHttpClient())
            .build()
            .create(DirectionApiService::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            builder.addInterceptor(logging)
        }

        return builder.build()
    }

    @Provides
    fun provideDirectionRepository(api: DirectionApiService): DirectionRepository =
        DirectionRepositoryImpl(api)

    @Provides
    fun provideGetDirectionsUseCase(repository: DirectionRepository): GetDirectionsUseCase =
        GetDirectionsUseCase(repository)
}