package com.example.thunder.modules.sampledata.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.thunder.components.InputField
import com.example.thunder.routes.Routes

@Composable
fun SearchView(modifier: Modifier = Modifier, navController: NavController) {
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
                BuildHeaderView(navController = navController)
            }
        }
    }
}

@Composable
fun BuildHeaderView(navController: NavController) {

    val cityTextFieldState = remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

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
                            Log.d("Search View", "Back tapped!!")
                        }
                )
            }
        }


        Surface(
            modifier = Modifier
                .padding(10.dp),
            color = Color.Black
        ) {
            InputField(
                modifier = Modifier,
                valueState = cityTextFieldState,
                topLabel = "Enter City or Country",
                enabled = true,
                isSingleLine = true,
            ) { finalString ->
                Log.d("Search View", "textfield value is $finalString")
            }
        }
    }

}