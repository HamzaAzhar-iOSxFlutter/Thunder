package com.example.thunder.modules.sampledata.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thunder.data.DataOrException
import com.example.thunder.model.Weather
import com.example.thunder.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val data: MutableState<DataOrException<Weather, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception("")))

    private val _favourites = mutableStateListOf<String>()
    val favourites: List<String> get() = _favourites
    var isFavourite by mutableStateOf(false)

    fun getWeather(cityName: String = "London") {
        viewModelScope.launch {
            viewModelScope.launch {
                data.value.loading = true
                data.value = repository.getWeather(city = cityName)

                if (data.value.data.toString().isNotEmpty()) {
                    data.value.loading = false
                    Log.d("HomeViewModel", "the data is ${data.value.data}")
                }
            }
        }
    }

    fun updateFavouriteState(cityName: String) {
        isFavourite = _favourites.contains(cityName)
    }

    fun addToFavourites(cityName: String) {
        if (!_favourites.contains(cityName)) {
            _favourites.add(cityName)
        }
    }

    fun removeFromFavourites(cityName: String) {
        _favourites.remove(cityName)
    }
}