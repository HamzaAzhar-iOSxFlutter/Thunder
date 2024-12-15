package com.example.thunder.repository

import android.util.Log
import com.example.thunder.data.DataOrException
import com.example.thunder.model.Weather
import com.example.thunder.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    private val dataOrException = DataOrException<Weather, Boolean, Exception>()

    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, java.lang.Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getWeatherDetails(query = city)

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
                Log.d("data", dataOrException.data.toString())
            }

        } catch(exception: Exception) {
            dataOrException.exception = exception
            Log.d("Exception", "getWeatherData: ${dataOrException.exception!!.localizedMessage}")
        }
        return dataOrException
    }
}