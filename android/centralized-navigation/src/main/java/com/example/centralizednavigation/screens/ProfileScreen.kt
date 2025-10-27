package com.example.centralizednavigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.centralizednavigation.Navigation

/**
 * プロフィール画面
 * 集中管理型：Navigation.Navigatorを使用して遷移
 */
@Composable
fun ProfileScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Screen",
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
        
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "ユーザー情報",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("名前: 田中太郎")
                Text("メール: tanaka@example.com")
                Text("年齢: 30歳")
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
                    navController?.let { Navigation.Navigator.navigateToHome(it) }
                }
            ) {
                Text("Home")
            }
            
            Button(
                onClick = { 
                    navController?.let { Navigation.Navigator.navigateToSettings(it) }
                }
            ) {
                Text("Settings")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { 
                navController?.let { Navigation.Navigator.navigateBack(it) }
            }
        ) {
            Text("Back")
        }
    }
}
