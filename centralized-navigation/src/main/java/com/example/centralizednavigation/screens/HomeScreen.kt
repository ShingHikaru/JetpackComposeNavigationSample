package com.example.centralizednavigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.centralizednavigation.Navigation

/**
 * ホーム画面
 * 集中管理型：Navigation.Navigatorを使用して遷移
 */
@Composable
fun HomeScreen(navController: NavController? = null) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "集中管理型ナビゲーション",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // ナビゲーションボタン
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { 
                    navController?.let { Navigation.Navigator.navigateToProfile(it) }
                }
            ) {
                Text("Profile")
            }
            
            Button(
                onClick = { 
                    navController?.let { Navigation.Navigator.navigateToSettings(it) }
                }
            ) {
                Text("Settings")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "アイテム一覧",
            style = MaterialTheme.typography.titleMedium
        )
        
        LazyColumn {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    onClick = { 
                        navController?.let { 
                            Navigation.Navigator.navigateToDetail(it, item)
                        }
                    }
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
