package com.example.thunder.modules.sampledata.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.thunder.R
import com.example.thunder.model.FavouritesManager
import com.example.thunder.model.Weather
import com.example.thunder.model.WeeklyForecast
import com.example.thunder.model.generateDummyWeeklyForecast
import com.example.thunder.routes.Routes
import com.example.thunder.utilities.displayDate

@Composable
fun HomeView(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val weatherData = viewModel.data.value.data

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val cityName = savedStateHandle?.getStateFlow<String>("cityName", "")?.collectAsState(initial = "")

    // Track favourite state, initializing it based on the weather data
    var isFavourite by remember {
        mutableStateOf(FavouritesManager.isFavourite(weatherData?.location?.country ?: ""))
    }

    // Update the favourite state when the weather data changes
    LaunchedEffect(weatherData) {
        weatherData?.let {
            isFavourite = FavouritesManager.isFavourite(it.location.country)
        }
    }

    // Trigger network request when cityName changes
    LaunchedEffect(cityName?.value) {
        if (cityName != null) {
            if (cityName.value.isNotEmpty()) {
                viewModel.getWeather(cityName.value)
            } else {
                viewModel.getWeather("London")
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding),
        ) {
            weatherData?.let { weather ->
                Column {
                    BuildWeatherHeader(
                        weatherData = weather,
                        navController = navController,
                        isFavourite = isFavourite,
                        onFavouriteClicked = {
                            // Toggle favourite state and update the manager
                            isFavourite = !isFavourite
                            if (isFavourite) {
                                FavouritesManager.addFavourite(weather.location.country)
                            } else {
                                FavouritesManager.removeFavourite(weather.location.country)
                            }
                        }
                    )
                    BuildWeatherCondition(weatherData = weather)
                    BuildWeatherMetrics(weatherData = weather)
                    BuildWeeklyWeatherForecastView(weatherData = weather)
                }
            }
        }
    }
}

/*
Responsible to build the header containing the location,
and the current date, with a search bar and a favourites button.
 */
@Composable
fun BuildWeatherHeader(
    weatherData: Weather,
    navController: NavController,
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit
) {
    Surface(
        color = Color.Black
    ) {
        // Main content goes here
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = Color.Black
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(
                        weatherData.location.country,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )

                    Text(
                        displayDate(localTime = weatherData.location.localtime),
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color.Black
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourites",
                        tint = if (isFavourite) Color.Red else Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onFavouriteClicked()
                            }
                    )
                }

                Surface(
                    color = Color.Black
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate(Routes.SearchView)
                            }
                    )
                }

                MinimalDropdownMenu(navController = navController)
            }
        }
    }
}


/*
Responsible to build Menu items with Favourites, Setting, and About,
performing respective segues on user selection
 */
@Composable
fun MinimalDropdownMenu(navController: NavController) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box{
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = Color.Gray)
        }
        DropdownMenu(
            modifier = Modifier
                .background(Color(0xFF212328)),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        "Favourites",
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                },
                onClick = {
                    expanded = false
                    navController.navigate(Routes.FavouritesView)
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        "About",
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                },
                onClick = {
                    expanded = false
                    navController.navigate(Routes.AboutView)
                }
            )
        }
    }

}

/*
This function is responsible to build the weather conditions,
such as the temperature and an svg image that indicates the weather type.
 */
@Composable
fun BuildWeatherCondition(weatherData: Weather) {

    Surface(
        color = Color.Black
    ) {
        //Main content goes here
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = Color.Black
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "${weatherData.current.temp_c.toInt()}°",
                        fontWeight = FontWeight.Bold,
                        fontSize = 90.sp,
                        color = Color.White
                    )

                    Text(
                        weatherData.current.condition.text,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                }
            }

            Surface(
                color = Color.Black
            ) {
                Image(
                    painter = painterResource(id = R.drawable.thunderstorm),
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(200.dp),
                    colorFilter = ColorFilter.tint(Color.Yellow)
                )
            }

        }
    }
}

@Composable
fun BuildWeatherMetrics(weatherData: Weather) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Black
    ) {
        Card(
            modifier = Modifier
                .padding(20.dp)
                .height(120.dp),
            colors = CardDefaults.cardColors(Color(0xFF212328)),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        color = Color.Transparent
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.wind),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "${weatherData.current.wind_mph.toInt()}m/s",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White
                        )

                        Text(
                            "Wind",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }


                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        color = Color.Transparent
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.humidity),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(Color.Cyan)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "${weatherData.current.humidity}%",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White
                        )


                        Text(
                            "Humidity",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        color = Color.Transparent
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pressure),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(Color.Magenta)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "${weatherData.current.pressure_in.toInt()}",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White
                        )


                        Text(
                            "Pressure",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }

                }
            }
        }
    }

}

@Composable
fun BuildWeeklyWeatherForecastView(weatherData: Weather) {

    val weeklyForecast = generateDummyWeeklyForecast()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            weeklyForecast.forEach { forecast ->
                BuildIndividualForecastRow(forecastData = forecast)
            }
        }
    }
}

/*
Method responsible to build and populate weekly vertical rows of weather conditions.
 */
@Composable
fun BuildIndividualForecastRow(forecastData: WeeklyForecast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = forecastData.day,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.weight(2f)
        )

        Text(
            text = forecastData.minTemperature,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.LightGray,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = forecastData.weatherCondition,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 8.dp)
        )

        Text(
            text = forecastData.maxTemperature,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Surface(
            color = Color.Transparent,
            modifier = Modifier.weight(0.5f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}