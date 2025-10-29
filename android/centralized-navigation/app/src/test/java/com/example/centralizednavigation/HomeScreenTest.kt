package com.example.centralizednavigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import com.example.centralizednavigation.screens.HomeScreen
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * 集中管理型HomeScreenのテスト
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
            HomeScreen(
                onNavigateToProfile = {},
                onNavigateToSettings = {},
                onNavigateToDetail = {},
                onNavigateToHomeWithClearBackStack = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
        composeTestRule.onNodeWithText("集中管理型ナビゲーション").assertIsDisplayed()
        composeTestRule.onNodeWithText("アイテム一覧").assertIsDisplayed()
        composeTestRule.onNodeWithText("MenuBar二回タップ処理のデモ").assertIsDisplayed()
    }

    @Test
    fun `HomeScreen should display navigation buttons`() {
        // When
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToProfile = {},
                onNavigateToSettings = {},
                onNavigateToDetail = {},
                onNavigateToHomeWithClearBackStack = {}
            )
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
            HomeScreen(
                onNavigateToProfile = {},
                onNavigateToSettings = {},
                onNavigateToDetail = {},
                onNavigateToHomeWithClearBackStack = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 4").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 5").assertIsDisplayed()
    }

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

    @Test
    fun `clicking Settings button should call onNavigateToSettings`() {
        // Given
        var settingsClicked = false
        
        // When
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToProfile = {},
                onNavigateToSettings = { settingsClicked = true },
                onNavigateToDetail = {},
                onNavigateToHomeWithClearBackStack = {}
            )
        }
        composeTestRule.onNodeWithText("Settings").performClick()

        // Then
        assertTrue("Settings button should be clicked", settingsClicked)
    }

    @Test
    fun `clicking item should call onNavigateToDetail with correct itemId`() {
        // Given
        var clickedItemId: String? = null
        
        // When
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToProfile = {},
                onNavigateToSettings = {},
                onNavigateToDetail = { itemId -> clickedItemId = itemId },
                onNavigateToHomeWithClearBackStack = {}
            )
        }
        composeTestRule.onNodeWithText("Item 1").performClick()

        // Then
        assertEquals("Item 1", clickedItemId)
    }

    @Test
    fun `clicking Clear BackStack button should call onNavigateToHomeWithClearBackStack`() {
        // Given
        var clearBackStackClicked = false
        
        // When
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToProfile = {},
                onNavigateToSettings = {},
                onNavigateToDetail = {},
                onNavigateToHomeWithClearBackStack = { clearBackStackClicked = true }
            )
        }
        composeTestRule.onNodeWithText("Clear BackStack & Go Home").performClick()

        // Then
        assertTrue("Clear BackStack button should be clicked", clearBackStackClicked)
    }

    @Test
    fun `HomeScreen should work with default parameters`() {
        // When
        composeTestRule.setContent {
            HomeScreen()
        }

        // Then - should not crash and display content
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
        composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }
}
