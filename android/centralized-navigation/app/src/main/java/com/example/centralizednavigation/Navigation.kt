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
            HomeScreen(navController = navController)
        }
        
        composable(Routes.PROFILE) {
            ProfileScreen(navController = navController)
        }
        
        composable(Routes.SETTINGS) {
            SettingsScreen(navController = navController)
        }
        
        composable(Routes.DETAIL) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            DetailScreen(itemId = itemId, navController = navController)
        }
    }
    
    /**
     * ナビゲーション関数群
     * 全ての画面遷移をここで一括管理
     */
    object Navigator {
        fun navigateToHome(navController: NavController) {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.HOME) { inclusive = true }
            }
        }
        
        fun navigateToProfile(navController: NavController) {
            navController.navigate(Routes.PROFILE)
        }
        
        fun navigateToSettings(navController: NavController) {
            navController.navigate(Routes.SETTINGS)
        }
        
        fun navigateToDetail(navController: NavController, itemId: String) {
            navController.navigate("detail/$itemId")
        }
        
        fun navigateBack(navController: NavController) {
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
            }
        }
        
        /**
         * MenuBarの二回タップでトップ画面に戻る処理
         * バックスタックをクリアしてホーム画面に遷移
         */
        fun navigateToHomeWithClearBackStack(navController: NavController) {
            navController.navigate(Routes.HOME) {
                popUpTo(0) { inclusive = false }
            }
        }
    }
}
