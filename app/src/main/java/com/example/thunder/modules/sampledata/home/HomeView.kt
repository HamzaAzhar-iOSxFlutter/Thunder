package com.example.thunder.modules.sampledata.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.thunder.R
import com.example.thunder.routes.Routes

@Composable
fun HomeView(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding),
        ) {
            Column {
                BuildWeatherHeader(navController = navController)
                BuildWeatherCondition()
                BuildWeatherMetrics()
                BuildWeeklyWeatherForecastView()
            }
        }
    }
}

/*
Responsible to build the header containing the location,
and the current date, with a search bar and a favourites button.
 */
@Composable
fun BuildWeatherHeader(navController: NavController) {

    Surface(
        color = Color.Black
    ) {
        //Main content goes here
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                color = Color.Black
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        "Stuttgart",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )

                    Text(
                        "12 September, Sunday",
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    color = Color.Black
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourites",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {}
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
                                navController.navigate(Routes.screenB)
                            }
                    )
                }
            }
        }
    }
}

/*
This function is responsible to build the weather conditions,
such as the temperature and an svg image that indicates the weather type.
 */
@Composable
fun BuildWeatherCondition() {

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
                        "18°",
                        fontWeight = FontWeight.Bold,
                        fontSize = 90.sp,
                        color = Color.White
                    )

                    Text(
                        "Thunderstorm",
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
fun BuildWeatherMetrics() {
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
                            "10m/s",
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
                            "98%",
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
                            painter = painterResource(id = R.drawable.rain),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(Color.Black)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "100%",
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White
                        )


                        Text(
                            "Rain",
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
fun BuildWeeklyWeatherForecastView() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            BuildIndividualForecastRow()
            BuildIndividualForecastRow()
            BuildIndividualForecastRow()
            BuildIndividualForecastRow()
            BuildIndividualForecastRow()
            BuildIndividualForecastRow()
            BuildIndividualForecastRow()
        }
    }
}

@Composable
fun BuildIndividualForecastRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Today",
            fontSize = 15.sp,
            color = Color.White
        )

        Text(
            "13°",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.LightGray
        )

        Text(
            "Rainy",
            color = Color.White
        )

        Text(
            "22°",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.White
        )

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
    }
}