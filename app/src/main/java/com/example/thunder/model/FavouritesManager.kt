package com.example.thunder.model

object FavouritesManager {
    private val favourites = mutableSetOf<String>() // Use a Set to avoid duplicates

    val dummyFavourites = mutableSetOf<String>(
        "London",
        "New York",
        "Tokyo",
        "Paris",
        "Sydney"
    )

    fun addFavourite(city: String) {
        favourites.add(city)
    }

    fun removeFavourite(city: String) {
        favourites.remove(city)
    }

    fun isFavourite(city: String): Boolean {
        return favourites.contains(city)
    }

    fun getFavourites(): List<String> {
        return favourites.toList()
    }
}