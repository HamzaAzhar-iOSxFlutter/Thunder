package com.example.thunder.modules.sampledata.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.thunder.components.InputField
import com.example.thunder.model.FavouritesManager
import com.example.thunder.modules.sampledata.home.BuildIndividualForecastRow
import com.example.thunder.modules.sampledata.search.BuildHeaderView
import com.example.thunder.utilities.AppColors

@Composable
fun FavouritesView(modifier: Modifier = Modifier, navController: NavController) {
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
                BuildFavouriteCardView(navController)
            }
        }
    }
}

@Composable
fun BuildFavouriteCardView(navController: NavController) {

    val favourites = FavouritesManager.getFavourites()

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

        Card(
            modifier = Modifier
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color.Black)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(favourites) { favourite ->
                    BuildIndividualFavouriteRow(favourite = favourite)
                }
            }
        }
    }
}

@Composable
fun BuildIndividualFavouriteRow(favourite: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(90.dp),
        colors = CardDefaults.cardColors(AppColors.mLightPurple),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                favourite,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 10.dp)
            )
        }
    }
}

























