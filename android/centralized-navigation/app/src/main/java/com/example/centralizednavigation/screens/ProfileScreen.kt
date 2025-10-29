package com.example.centralizednavigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.centralizednavigation.Navigation
import com.example.centralizednavigation.ui.theme.CentralizedNavigationTheme

/**
 * プロフィール画面
 * 集中管理型：関数型の引数でナビゲーション処理を受け取る
 */
@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
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
                onClick = onNavigateToHome
            ) {
                Text("Home")
            }
            
            Button(
                onClick = onNavigateToSettings
            ) {
                Text("Settings")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = onNavigateBack
        ) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CentralizedNavigationTheme {
        ProfileScreen(
            onNavigateToHome = { /* Preview用のモック */ },
            onNavigateToSettings = { /* Preview用のモック */ },
            onNavigateBack = { /* Preview用のモック */ }
        )
    }
}
