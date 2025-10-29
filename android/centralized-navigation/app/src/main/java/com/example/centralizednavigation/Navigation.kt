package com.example.centralizednavigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.centralizednavigation.screens.HomeScreen
import com.example.centralizednavigation.screens.ProfileScreen
import com.example.centralizednavigation.screens.SettingsScreen
import com.example.centralizednavigation.screens.DetailScreen

/**
 * 集中管理型ナビゲーション
 * 全ての遷移処理をこのファイルで一括管理
 */
object Navigation {
    
    // 画面ルート定義
    object Routes {
        const val HOME = "home"
        const val PROFILE = "profile"
        const val SETTINGS = "settings"
        const val DETAIL = "detail/{itemId}"
        const val DETAIL_WITHOUT_PARAM = "detail"
    }
    
    /**
     * ナビゲーショングラフの構築
     */
    fun NavGraphBuilder.setupNavigation(navController: NavController) {
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToProfile = { navController.navigateToProfile() },
                onNavigateToSettings = { navController.navigateToSettings() },
                onNavigateToDetail = { itemId -> navController.navigateToDetail(itemId) },
                onNavigateToHomeWithClearBackStack = { navController.navigateToHomeWithClearBackStack() }
            )
        }
        
        composable(Routes.PROFILE) {
            ProfileScreen(
                onNavigateToHome = { navController.navigateToHome() },
                onNavigateToSettings = { navController.navigateToSettings() },
                onNavigateBack = { navController.navigateBack() }
            )
        }
        
        composable(Routes.SETTINGS) {
            SettingsScreen(
                onNavigateToHome = { navController.navigateToHome() },
                onNavigateToProfile = { navController.navigateToProfile() },
                onNavigateBack = { navController.navigateBack() }
            )
        }
        
        composable(Routes.DETAIL) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            DetailScreen(
                itemId = itemId,
                onNavigateToHome = { navController.navigateToHome() },
                onNavigateToProfile = { navController.navigateToProfile() },
                onNavigateToSettings = { navController.navigateToSettings() },
                onNavigateBack = { navController.navigateBack() }
            )
        }
    }
    
    /**
     * ナビゲーション関数群
     * NavControllerのextensionとして定義し、より簡潔なAPIを提供
     */
    object Navigator {
        /**
         * ホーム画面に遷移
         */
        fun NavController.navigateToHome() {
            navigate(Routes.HOME) {
                popUpTo(Routes.HOME) { inclusive = true }
            }
        }
        
        /**
         * プロフィール画面に遷移
         */
        fun NavController.navigateToProfile() {
            navigate(Routes.PROFILE)
        }
        
        /**
         * 設定画面に遷移
         */
        fun NavController.navigateToSettings() {
            navigate(Routes.SETTINGS)
        }
        
        /**
         * 詳細画面に遷移（パラメータ付き）
         */
        fun NavController.navigateToDetail(itemId: String) {
            navigate("detail/$itemId")
        }
        
        /**
         * 前の画面に戻る
         */
        fun NavController.navigateBack() {
            if (previousBackStackEntry != null) {
                popBackStack()
            }
        }
        
        /**
         * MenuBarの二回タップでトップ画面に戻る処理
         * バックスタックをクリアしてホーム画面に遷移
         */
        fun NavController.navigateToHomeWithClearBackStack() {
            navigate(Routes.HOME) {
                popUpTo(0) { inclusive = false }
            }
        }
    }
}
