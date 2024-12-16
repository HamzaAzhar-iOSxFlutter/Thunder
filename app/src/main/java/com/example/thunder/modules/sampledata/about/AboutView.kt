package com.example.thunder.modules.sampledata.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.example.thunder.utilities.AppColors

@Composable
fun AboutView(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding),
            color = Color.Black
        ) {
            Column {
                BuildAboutView(navController = navController)
            }
        }
    }
}

@Composable
fun BuildAboutView(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        //Back Button
        Surface(
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp),
            color = Color(0xFF212328),
            shape = CircleShape,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }
        }

        InfoCard()
    }
}

@Composable
fun InfoCard() {
    // Get the screen height
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(0.6f * screenHeight),
        colors = CardDefaults.cardColors(AppColors.mLightPurple),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Center content within the box
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Center text horizontally
                verticalArrangement = Arrangement.Center, // Center text vertically
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Welcome to the Weather App",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp) // Add space between the title and description
                )

                Text(
                    "This app allows you to easily keep track of weather conditions in your favorite cities. Add and remove locations to see live updates on weather information.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(horizontal = 20.dp) // Ensure text is not too close to the edges
                )
            }
        }
    }
}

