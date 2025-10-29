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
 * 詳細画面
 * 集中管理型：関数型の引数でナビゲーション処理を受け取る
 */
@Composable
fun DetailScreen(
    itemId: String,
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
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
            text = "Detail Screen",
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
                onClick = onNavigateToHome
            ) {
                Text("Home")
            }
            
            Button(
                onClick = onNavigateToProfile
            ) {
                Text("Profile")
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
fun DetailScreenPreview() {
    CentralizedNavigationTheme {
        DetailScreen(
            itemId = "preview-item-123",
            onNavigateToHome = { /* Preview用のモック */ },
            onNavigateToProfile = { /* Preview用のモック */ },
            onNavigateToSettings = { /* Preview用のモック */ },
            onNavigateBack = { /* Preview用のモック */ }
        )
    }
}
