package com.mynt.app.moviehub.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mynt.app.moviehub.ui.main.movies.moviesScreen
import com.mynt.app.moviehub.ui.main.movies.moviesScreenRoute
import com.mynt.app.moviehub.ui.theme.MovieHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            MovieHubTheme {
                Scaffold { padding ->
                    MainNavHost(
                        navHostController = navController,
                        modifier = Modifier.padding(padding)
                    )

                }
            }
        }
    }
}

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = moviesScreenRoute,
        modifier = modifier
    ) {
        moviesScreen()
    }
}
