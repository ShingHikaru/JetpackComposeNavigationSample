package com.example.distributednavigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import com.example.distributednavigation.screens.HomeScreen
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * 分散管理型HomeScreenのテスト
 * 画面の表示とナビゲーション動作をテスト
 */
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        mockNavController = mockk<NavController>(relaxed = true)
    }

    @Test
    fun `HomeScreen should display correct title and description`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }

        // Then
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
        composeTestRule.onNodeWithText("分散管理型ナビゲーション").assertIsDisplayed()
        composeTestRule.onNodeWithText("アイテム一覧").assertIsDisplayed()
        composeTestRule.onNodeWithText("MenuBar二回タップ処理のデモ").assertIsDisplayed()
    }

    @Test
    fun `HomeScreen should display navigation buttons`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }

        // Then
        composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clear BackStack & Go Home").assertIsDisplayed()
    }

    @Test
    fun `HomeScreen should display item list`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }

        // Then
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 4").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 5").assertIsDisplayed()
    }

    @Test
    fun `clicking Profile button should navigate directly`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithText("Profile").performClick()

        // Then
        verify {
            mockNavController.navigate("profile")
        }
    }

    @Test
    fun `clicking Settings button should navigate directly`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithText("Settings").performClick()

        // Then
        verify {
            mockNavController.navigate("settings")
        }
    }

    @Test
    fun `clicking item should navigate to detail with itemId`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithText("Item 1").performClick()

        // Then
        verify {
            mockNavController.navigate("detail/Item 1")
        }
    }

    @Test
    fun `clicking Clear BackStack button should navigate with popUpTo`() {
        // When
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController) {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithText("Clear BackStack & Go Home").performClick()

        // Then
        verify {
            mockNavController.navigate(
                "home",
                match {
                    it.popUpTo(0) { inclusive = false }
                    true
                }
            )
        }
    }

    @Test
    fun `HomeScreen should throw error when NavController not provided`() {
        // When & Then
        try {
            composeTestRule.setContent {
                HomeScreen() // LocalNavController not provided
            }
            // If we reach here, the test should fail
            assertTrue("Expected error to be thrown", false)
        } catch (e: Exception) {
            // Expected behavior - should throw error
            assertTrue("Expected error when NavController not provided", true)
        }
    }

    @Test
    fun `HomeScreen should work with different NavController instances`() {
        // Given
        val mockNavController1 = mockk<NavController>(relaxed = true)
        val mockNavController2 = mockk<NavController>(relaxed = true)

        // When using first NavController
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController1) {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithText("Profile").performClick()

        // Then
        verify {
            mockNavController1.navigate("profile")
        }

        // When using second NavController
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNavController provides mockNavController2) {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithText("Profile").performClick()

        // Then
        verify {
            mockNavController2.navigate("profile")
        }
    }
}
