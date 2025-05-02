package com.example.navigationtest.hilt.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("person")
    fun porviderStringName(): String {
        return "hosein"
    }

    @Provides
    @Singleton
    @Named("car")
    fun porviderStringCar(): String {
        return "Bmw"
    }

//    @Provides
//    @Singleton
//    fun provideDataBase(application: Application): AppDatabase =
//        Room.databaseBuilder(application, AppDatabase::class.java, "Note_Database").build()
}