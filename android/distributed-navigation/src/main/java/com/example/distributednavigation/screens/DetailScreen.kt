package com.example.distributednavigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.distributednavigation.LocalNavController

/**
 * 詳細画面
 * 分散管理型：LocalNavController.currentを使用して遷移
 */
@Composable
fun DetailScreen(itemId: String) {
    val navController = LocalNavController.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detail Screen",
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
        
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "アイテム詳細",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("ID: $itemId")
                Text("説明: これは$itemId の詳細情報です")
                Text("作成日: 2024年1月1日")
                Text("更新日: 2024年1月15日")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // ナビゲーションボタン
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { 
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            ) {
                Text("Home")
            }
            
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
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { 
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }
        ) {
            Text("Back")
        }
    }
}
