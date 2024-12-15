package com.example.thunder.di

import com.example.thunder.environment.Environment
import com.example.thunder.network.WeatherApi
import com.example.thunder.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/*
@Module: Marks this class as a provider of dependencies.
@InstallIn(SingletonComponent::class): The dependencies provided are available as singletons for the entire app lifecycle.
@Provides: Specifies how to create a dependency.
provideWeatherApi(): Creates a WeatherApi instance using Retrofit with a base URL and a JSON converter.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepository(api)
    }

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Environment.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}