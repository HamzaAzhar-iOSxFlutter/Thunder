package com.example.thunder.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thunder.modules.sampledata.home.HomeView
import com.example.thunder.modules.sampledata.search.SearchView
import com.example.thunder.routes.Routes
import com.example.thunder.ui.theme.ThunderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThunderTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = Routes.screenA, builder = {
                        composable(route = Routes.screenA) {
                            HomeView(modifier = Modifier, navController = navController)
                        }

                        composable(route = Routes.screenB) {
                            SearchView(navController = navController)
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
