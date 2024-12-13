package com.example.thunder.modules.sampledata.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.thunder.R

@Preview
@Composable
fun HomeView(modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
       Surface(
           modifier = modifier.padding(innerPadding),
       ) {
           Column {
               BuildWeatherHeader()
               BuildWeatherCondition()
           }
       }
    }
}

@Preview
@Composable
fun BuildWeatherHeader() {

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

            //Search Icon
            Surface(
                color = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {}
                )
            }
        }
    }
}

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
                    colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.Yellow)
                )
            }

        }
    }
}
