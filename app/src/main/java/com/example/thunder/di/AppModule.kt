package com.example.thunder.di

import android.util.Log
import com.example.thunder.environment.Environment
import com.example.thunder.network.WeatherApi
import com.example.thunder.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

    val loggingInterceptor = Interceptor { chain ->
        val request = chain.request()
        Log.d("RetrofitURL", "Request URL: ${request.url()}") // Logs the final URL
        chain.proceed(request)
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}