# Jetpack Compose Navigation 比較プロジェクト

このプロジェクトは、Jetpack ComposeにおけるNavigationの管理方法の違いを実装で示すためのサンプルプロジェクトです。

## プロジェクト構成

```
JetpackComposeNavigationSample/
├── centralized-navigation/     # 集中管理型（YesDir）
└── distributed-navigation/    # 分散管理型（NoDir）
```

## 実装方針の比較

### 1. 集中管理型（centralized-navigation）

**特徴:**
- 全ての遷移処理を`Navigation.kt`で一括管理
- `Navigation.Navigator`オブジェクトで遷移関数を提供
- 画面ルートを`Navigation.Routes`で定義
- 各画面は`Navigation.Navigator`を使用して遷移

**メリット:**
- ✅ 遷移ロジックが一箇所に集約されている
- ✅ ルート名の管理が容易
- ✅ 遷移処理の変更が一箇所で済む
- ✅ テストが容易
- ✅ デバッグが容易

**デメリット:**
- ❌ 初期実装がやや複雑
- ❌ 画面ごとにNavControllerを渡す必要がある

### 2. 分散管理型（distributed-navigation）

**特徴:**
- 各画面で`LocalNavController.current`を使用
- `CompositionLocal`でNavControllerを提供
- 各画面が直接`navController.navigate()`を呼び出し
- 遷移処理が各画面に分散

**メリット:**
- ✅ 実装がシンプル
- ✅ 画面ごとに独立した遷移処理
- ✅ 学習コストが低い

**デメリット:**
- ❌ 遷移処理が各画面に分散している
- ❌ ルート名の重複やタイポのリスク
- ❌ 遷移処理の変更時に複数箇所を修正する必要
- ❌ テストが困難
- ❌ デバッグが困難
- ❌ バケツリレー問題（NavControllerを各画面に渡す必要）

## 実装の詳細比較

### 集中管理型の実装例

```kotlin
// Navigation.kt
object Navigation {
    object Routes {
        const val HOME = "home"
        const val PROFILE = "profile"
    }
    
    object Navigator {
        fun navigateToProfile(navController: NavController) {
            navController.navigate(Routes.PROFILE)
        }
    }
}

// HomeScreen.kt
@Composable
fun HomeScreen(navController: NavController? = null) {
    Button(
        onClick = { 
            navController?.let { Navigation.Navigator.navigateToProfile(it) }
        }
    ) {
        Text("Profile")
    }
}
```

### 分散管理型の実装例

```kotlin
// LocalNavController.kt
val LocalNavController = staticCompositionLocalOf<NavController> {
    error("No NavController provided")
}

// HomeScreen.kt
@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    
    Button(
        onClick = { 
            navController.navigate("profile")
        }
    ) {
        Text("Profile")
    }
}
```

## バケツリレー問題の解決

### 集中管理型での解決
- `Navigation.Navigator`を使用することで、NavControllerの受け渡しを最小限に抑制
- 各画面は`Navigation.Navigator`の関数を呼び出すだけ

### 分散管理型での問題
- `LocalNavController.current`を使用するものの、各画面で直接`navController.navigate()`を呼び出し
- 遷移処理が各画面に分散し、保守性が低下

## 推奨事項

**大規模なアプリケーションでは集中管理型を推奨**

理由:
1. **保守性**: 遷移処理の変更が一箇所で済む
2. **テスタビリティ**: 遷移ロジックのテストが容易
3. **デバッガビリティ**: 遷移処理の追跡が容易
4. **スケーラビリティ**: 画面数が増えても管理が容易

**小規模なアプリケーションでは分散管理型も選択肢**

理由:
1. **シンプルさ**: 実装が簡単
2. **学習コスト**: 理解しやすい

## 実行方法

各ディレクトリで以下のコマンドを実行:

```bash
# 集中管理型
cd centralized-navigation
./gradlew assembleDebug

# 分散管理型
cd distributed-navigation
./gradlew assembleDebug
```

## 結論

このプロジェクトは、Jetpack ComposeにおけるNavigation管理の2つのアプローチを実装で示し、それぞれのメリット・デメリットを明確にしています。特に、集中管理型アプローチが大規模アプリケーションにおいて優位性を持つことを実装レベルで示しています。
