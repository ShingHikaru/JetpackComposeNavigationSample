# 関数型API改善 - Preview対応とテスタビリティ向上

## 概要

`NavController`を引数として受け取るのではなく、関数型の引数として受け取ることで、Previewでの表示が可能になり、テスタビリティも大幅に向上しました。

## 改善前後の比較

### 改善前（NavControllerを引数として受け取る方式）

```kotlin
@Composable
fun HomeScreen(navController: NavController) {
    Button(
        onClick = { 
            navController?.navigateToProfile()
        }
    ) {
        Text("Profile")
    }
}

// Previewで使用する場合
@Preview
@Composable
fun HomeScreenPreview() {
    // NavControllerが必要で、Previewで表示できない
    // HomeScreen(navController = ???) // エラー！
}
```

### 改善後（関数型の引数として受け取る方式）

```kotlin
@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToDetail: (String) -> Unit = {},
    onNavigateToHomeWithClearBackStack: () -> Unit = {}
) {
    Button(
        onClick = onNavigateToProfile
    ) {
        Text("Profile")
    }
}

// Previewで使用する場合
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CentralizedNavigationTheme {
        HomeScreen(
            onNavigateToProfile = { /* Preview用のモック */ },
            onNavigateToSettings = { /* Preview用のモック */ },
            onNavigateToDetail = { /* Preview用のモック */ },
            onNavigateToHomeWithClearBackStack = { /* Preview用のモック */ }
        )
    }
}
```

## 改善のメリット

### 1. Preview対応

#### 改善前
```kotlin
// Previewで表示できない
@Preview
@Composable
fun HomeScreenPreview() {
    // NavControllerが必要で、Previewで表示できない
    HomeScreen(navController = ???) // エラー！
}
```

#### 改善後
```kotlin
// Previewで表示可能
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CentralizedNavigationTheme {
        HomeScreen(
            onNavigateToProfile = { /* Preview用のモック */ },
            onNavigateToSettings = { /* Preview用のモック */ },
            onNavigateToDetail = { /* Preview用のモック */ },
            onNavigateToHomeWithClearBackStack = { /* Preview用のモック */ }
        )
    }
}
```

**メリット:**
- ✅ Previewでコンポーネントを視覚的に確認可能
- ✅ デザインの確認が容易
- ✅ 開発効率の向上

### 2. テスタビリティの向上

#### 改善前
```kotlin
@Test
fun `clicking Profile button should call Navigation Navigator`() {
    // Given
    val mockNavController = mockk<NavController>(relaxed = true)
    
    // When
    composeTestRule.setContent {
        HomeScreen(navController = mockNavController)
    }
    composeTestRule.onNodeWithText("Profile").performClick()

    // Then
    verify {
        mockNavController.navigateToProfile()
    }
}
```

#### 改善後
```kotlin
@Test
fun `clicking Profile button should call onNavigateToProfile`() {
    // Given
    var profileClicked = false
    
    // When
    composeTestRule.setContent {
        HomeScreen(
            onNavigateToProfile = { profileClicked = true },
            onNavigateToSettings = {},
            onNavigateToDetail = {},
            onNavigateToHomeWithClearBackStack = {}
        )
    }
    composeTestRule.onNodeWithText("Profile").performClick()

    // Then
    assertTrue("Profile button should be clicked", profileClicked)
}
```

**メリット:**
- ✅ モックの設定が簡単
- ✅ テストの意図が明確
- ✅ テストの実行速度が高速
- ✅ 依存関係が少ない

### 3. コンポーネントの独立性

#### 改善前
```kotlin
// NavControllerに依存
@Composable
fun HomeScreen(navController: NavController) {
    // NavControllerがnullの場合の処理が必要
    navController?.navigateToProfile()
}
```

#### 改善後
```kotlin
// 関数型の引数に依存（デフォルト値あり）
@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToDetail: (String) -> Unit = {},
    onNavigateToHomeWithClearBackStack: () -> Unit = {}
) {
    // デフォルト値があるため、nullチェック不要
    onNavigateToProfile()
}
```

**メリット:**
- ✅ デフォルト値により、nullチェック不要
- ✅ コンポーネントの独立性が高い
- ✅ 再利用性が高い

### 4. 型安全性の向上

#### 改善前
```kotlin
// 文字列リテラルでルート名を指定
navController.navigate("profile")
```

#### 改善後
```kotlin
// 関数型の引数で型安全
onNavigateToProfile: () -> Unit
onNavigateToDetail: (String) -> Unit
```

**メリット:**
- ✅ コンパイル時に型チェック
- ✅ タイポのリスクがない
- ✅ IDEの自動補完が効く

## 実装の詳細

### 1. 画面コンポーネントの定義

```kotlin
@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToDetail: (String) -> Unit = {},
    onNavigateToHomeWithClearBackStack: () -> Unit = {}
) {
    // 画面の実装
    Button(onClick = onNavigateToProfile) {
        Text("Profile")
    }
}
```

### 2. Navigation.ktでの使用

```kotlin
fun NavGraphBuilder.setupNavigation(navController: NavController) {
    composable(Routes.HOME) {
        HomeScreen(
            onNavigateToProfile = { navController.navigateToProfile() },
            onNavigateToSettings = { navController.navigateToSettings() },
            onNavigateToDetail = { itemId -> navController.navigateToDetail(itemId) },
            onNavigateToHomeWithClearBackStack = { navController.navigateToHomeWithClearBackStack() }
        )
    }
}
```

### 3. Previewの実装

```kotlin
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CentralizedNavigationTheme {
        HomeScreen(
            onNavigateToProfile = { /* Preview用のモック */ },
            onNavigateToSettings = { /* Preview用のモック */ },
            onNavigateToDetail = { /* Preview用のモック */ },
            onNavigateToHomeWithClearBackStack = { /* Preview用のモック */ }
        )
    }
}
```

### 4. テストの実装

```kotlin
@Test
fun `clicking Profile button should call onNavigateToProfile`() {
    // Given
    var profileClicked = false
    
    // When
    composeTestRule.setContent {
        HomeScreen(
            onNavigateToProfile = { profileClicked = true },
            onNavigateToSettings = {},
            onNavigateToDetail = {},
            onNavigateToHomeWithClearBackStack = {}
        )
    }
    composeTestRule.onNodeWithText("Profile").performClick()

    // Then
    assertTrue("Profile button should be clicked", profileClicked)
}
```

## パフォーマンスへの影響

### 1. コンパイル時

- **関数型の引数**: コンパイル時に静的に解決される
- **実行時オーバーヘッド**: なし
- **メモリ使用量**: 変化なし

### 2. 実行時

- **呼び出しコスト**: 通常の関数呼び出しと同等
- **最適化**: Kotlinコンパイラによって最適化される

## 互換性

### 1. 既存コードとの互換性

- **段階的移行**: 既存のコードを段階的に移行可能
- **後方互換性**: 既存のAPIも引き続き使用可能

### 2. チーム開発での採用

- **学習コスト**: 低い（Kotlinの標準的な機能）
- **採用しやすさ**: 高い（直感的なAPI）

## 推奨事項

### 1. 新規プロジェクト

**関数型API方式を強く推奨**

理由:
- Previewでの視覚的確認が可能
- 高いテスタビリティ
- コンポーネントの独立性
- 型安全性の向上

### 2. 既存プロジェクト

**段階的な移行を推奨**

手順:
1. 新しい画面から関数型API方式を採用
2. 既存の画面を段階的に移行
3. Previewを追加してデザインの確認を容易にする
4. テストを更新してテスタビリティを向上

### 3. チーム開発

**コーディング規約の策定を推奨**

- 関数型引数の命名規則（`on`プレフィックス）
- Previewの実装標準
- テストの書き方の統一

## 結論

関数型の引数として受け取ることで、以下の改善が実現されました：

1. **Preview対応**: コンポーネントを視覚的に確認可能
2. **テスタビリティの向上**: より簡潔で理解しやすいテスト
3. **コンポーネントの独立性**: 高い再利用性
4. **型安全性の向上**: コンパイル時の型チェック
5. **開発効率の向上**: より直感的なAPI設計

この改善により、集中管理型ナビゲーションの利点を維持しながら、Previewでの視覚的確認と高いテスタビリティを実現できるようになりました。特に、大規模なアプリケーションでの開発効率と保守性が大幅に向上します。
