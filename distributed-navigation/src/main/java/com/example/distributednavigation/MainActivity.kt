package com.example.distributednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.distributednavigation.screens.DetailScreen
import com.example.distributednavigation.screens.HomeScreen
import com.example.distributednavigation.screens.ProfileScreen
import com.example.distributednavigation.screens.SettingsScreen
import com.example.distributednavigation.ui.theme.DistributedNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistributedNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DistributedNavigationApp()
                }
            }
        }
    }
}

@Composable
fun DistributedNavigationApp() {
    val navController = rememberNavController()
    
    // CompositionLocalProviderでNavControllerを提供
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen()
            }
            
            composable("profile") {
                ProfileScreen()
            }
            
            composable("settings") {
                SettingsScreen()
            }
            
            composable("detail/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
                DetailScreen(itemId = itemId)
            }
        }
    }
}
