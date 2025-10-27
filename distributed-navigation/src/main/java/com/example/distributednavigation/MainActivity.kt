package com.example.distributednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    
    // CompositionLocalProviderでNavControllerを提供
    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(
            topBar = {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    Tab(
                        selected = selectedTabIndex == 0,
                        onClick = {
                            // 二回タップでClear BackStack機能
                            if (selectedTabIndex == 0) {
                                // 既にHomeタブが選択されている場合、Clear BackStackを実行
                                navController.navigate("home") {
                                    popUpTo(0) { inclusive = false }
                                }
                            } else {
                                // 初回タップの場合は通常のナビゲーション
                                navController.navigate("home")
                                selectedTabIndex = 0
                            }
                        },
                        text = {
                            Text(
                                text = "Home",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(contentPadding)
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
}
