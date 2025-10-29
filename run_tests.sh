#!/bin/bash

# Jetpack Compose Navigation テスト実行スクリプト
# 集中管理型と分散管理型のテストを実行し、結果を比較

echo "=========================================="
echo "Jetpack Compose Navigation テスト実行"
echo "=========================================="

# 集中管理型のテスト実行
echo ""
echo "1. 集中管理型（Centralized Navigation）のテスト実行"
echo "--------------------------------------------------"
cd android/centralized-navigation

echo "依存関係の同期..."
./gradlew --refresh-dependencies

echo "テストの実行..."
./gradlew test --info

if [ $? -eq 0 ]; then
    echo "✅ 集中管理型のテストが成功しました"
else
    echo "❌ 集中管理型のテストが失敗しました"
fi

# 分散管理型のテスト実行
echo ""
echo "2. 分散管理型（Distributed Navigation）のテスト実行"
echo "--------------------------------------------------"
cd ../distributed-navigation

echo "依存関係の同期..."
./gradlew --refresh-dependencies

echo "テストの実行..."
./gradlew test --info

if [ $? -eq 0 ]; then
    echo "✅ 分散管理型のテストが成功しました"
else
    echo "❌ 分散管理型のテストが失敗しました"
fi

# テスト結果の比較
echo ""
echo "3. テスト結果の比較"
echo "------------------"
echo "集中管理型のテスト結果:"
if [ -f "../centralized-navigation/app/build/reports/tests/testDebugUnitTest/index.html" ]; then
    echo "  - テストレポート: android/centralized-navigation/app/build/reports/tests/testDebugUnitTest/index.html"
else
    echo "  - テストレポートが見つかりません"
fi

echo ""
echo "分散管理型のテスト結果:"
if [ -f "../distributed-navigation/app/build/reports/tests/testDebugUnitTest/index.html" ]; then
    echo "  - テストレポート: android/distributed-navigation/app/build/reports/tests/testDebugUnitTest/index.html"
else
    echo "  - テストレポートが見つかりません"
fi

echo ""
echo "4. テスタビリティの比較"
echo "----------------------"
echo "集中管理型の利点:"
echo "  ✅ ビジネスロジックを直接テスト可能"
echo "  ✅ テストの保守性が高い"
echo "  ✅ テストの実行速度が高速"
echo "  ✅ テストの信頼性が高い"
echo "  ✅ 高いテストカバレッジが可能"

echo ""
echo "分散管理型の問題点:"
echo "  ❌ 間接的なテストのみ可能"
echo "  ❌ テストの保守性が低い"
echo "  ❌ テストの実行速度が低速"
echo "  ❌ テストの信頼性が低い"
echo "  ❌ 限定的なテストカバレッジ"

echo ""
echo "=========================================="
echo "テスト実行完了"
echo "=========================================="
echo ""
echo "詳細な比較については、TESTABILITY_COMPARISON.mdを参照してください。"

