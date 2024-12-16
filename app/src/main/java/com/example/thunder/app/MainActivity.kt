package com.example.thunder.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thunder.modules.sampledata.about.AboutView
import com.example.thunder.modules.sampledata.favourites.FavouritesView
import com.example.thunder.modules.sampledata.home.HomeView
import com.example.thunder.modules.sampledata.search.SearchView
import com.example.thunder.routes.Routes
import com.example.thunder.ui.theme.ThunderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThunderTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = Routes.HomeView, builder = {
                        composable(route = Routes.HomeView) {
                            HomeView(modifier = Modifier, navController = navController)
                        }

                        composable(route = Routes.SearchView) {
                            SearchView(navController = navController)
                        }

                        composable(route = Routes.FavouritesView) {
                            FavouritesView(navController = navController)
                        }

                        composable(route = Routes.AboutView) {
                            AboutView(navController = navController)
                        }
                    }
                )
            }
        }
    }
}

//@Composable
//fun MyApp(content: @Composable () -> Unit) {
//    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//        HomeView(modifier = Modifier.padding(innerPadding))
//    }
//}
