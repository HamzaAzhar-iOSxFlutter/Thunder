package com.example.thunder.network

import com.example.thunder.environment.Environment
import com.example.thunder.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeatherDetails(
        @Query("key") apiKey: String = Environment.ApiKey,
        @Query("q") query: String,
        @Query("days") days: Int = 10,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Weather
}

//https://api.weatherapi.com/v1/forecast.json?key=ba125a4741684032b5b31544230208&q=London&days=3&aqi=no&alerts=no