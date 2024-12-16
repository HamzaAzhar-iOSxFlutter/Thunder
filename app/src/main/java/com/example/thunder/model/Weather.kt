package com.example.thunder.model

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

data class WeeklyForecast (
    val day: String,
    val minTemperature: String,
    val maxTemperature: String,
    val weatherCondition: String,
    val conditionImage: String
)

fun generateDummyWeeklyForecast(): List<WeeklyForecast> {
    return listOf(
        WeeklyForecast(
            day = "Monday",
            minTemperature = "10°",
            maxTemperature = "20°",
            weatherCondition = "Cloudy",
            conditionImage = "https://example.com/partly_cloudy.png"
        ),
        WeeklyForecast(
            day = "Tuesday",
            minTemperature = "12°",
            maxTemperature = "22°",
            weatherCondition = "Sunny",
            conditionImage = "https://example.com/sunny.png"
        ),
        WeeklyForecast(
            day = "Wednesday",
            minTemperature = "9°",
            maxTemperature = "19°",
            weatherCondition = "Rainy",
            conditionImage = "https://example.com/rainy.png"
        ),
        WeeklyForecast(
            day = "Thursday",
            minTemperature = "11°",
            maxTemperature = "21°",
            weatherCondition = "Cloudy",
            conditionImage = "https://example.com/cloudy.png"
        ),
        WeeklyForecast(
            day = "Friday",
            minTemperature = "13°",
            maxTemperature = "23°",
            weatherCondition = "Windy",
            conditionImage = "https://example.com/windy.png"
        ),
        WeeklyForecast(
            day = "Saturday",
            minTemperature = "8°",
            maxTemperature = "18°",
            weatherCondition = "Stormy",
            conditionImage = "https://example.com/stormy.png"
        ),
        WeeklyForecast(
            day = "Sunday",
            minTemperature = "10°",
            maxTemperature = "20°",
            weatherCondition = "Sunny",
            conditionImage = "https://example.com/sunny.png"
        )
    )
}
