package com.example.distributednavigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.distributednavigation.LocalNavController

/**
 * ホーム画面
 * 分散管理型：LocalNavController.currentを使用して遷移
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
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
            text = "分散管理型ナビゲーション",
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
                    navController.navigate("profile")
                }
            ) {
                Text("Profile")
            }
            
            Button(
                onClick = { 
                    navController.navigate("settings")
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
                        navController.navigate("detail/$item")
                    }
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // MenuBarの二回タップでトップ画面に戻る処理のデモ
        Text(
            text = "MenuBar二回タップ処理のデモ",
            style = MaterialTheme.typography.titleMedium
        )
        
        Button(
            onClick = { 
                // 分散管理型：各画面で直接実装が必要
                navController.navigate("home") {
                    popUpTo(0) { inclusive = false }
                }
            }
        ) {
            Text("Clear BackStack & Go Home")
        }
    }
}
