package com.example.distributednavigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

/**
 * 分散管理型ナビゲーション
 * CompositionLocalを使用してNavControllerを各画面で直接取得
 */
val LocalNavController = staticCompositionLocalOf<NavController> {
    error("No NavController provided")
}
