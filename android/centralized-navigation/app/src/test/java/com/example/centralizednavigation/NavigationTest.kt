package com.example.centralizednavigation

import androidx.navigation.NavController
import com.example.centralizednavigation.Navigation
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

/**
 * 集中管理型ナビゲーションのテスト
 * Navigation.Navigatorの各関数の動作をテスト
 */
class NavigationTest {

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        mockNavController = mockk<NavController>(relaxed = true)
    }

    @Test
    fun `navigateToHome should navigate to home route`() {
        // When
        mockNavController.navigateToHome()

        // Then
        verify {
            mockNavController.navigate(
                Navigation.Routes.HOME,
                match {
                    it.popUpTo(Navigation.Routes.HOME) { inclusive = true }
                    true
                }
            )
        }
    }

    @Test
    fun `navigateToProfile should navigate to profile route`() {
        // When
        mockNavController.navigateToProfile()

        // Then
        verify {
            mockNavController.navigate(Navigation.Routes.PROFILE)
        }
    }

    @Test
    fun `navigateToSettings should navigate to settings route`() {
        // When
        mockNavController.navigateToSettings()

        // Then
        verify {
            mockNavController.navigate(Navigation.Routes.SETTINGS)
        }
    }

    @Test
    fun `navigateToDetail should navigate to detail route with itemId`() {
        // Given
        val itemId = "test-item-123"

        // When
        mockNavController.navigateToDetail(itemId)

        // Then
        verify {
            mockNavController.navigate("detail/$itemId")
        }
    }

    @Test
    fun `navigateBack should call popBackStack when previous entry exists`() {
        // Given
        every { mockNavController.previousBackStackEntry } returns mockk()

        // When
        mockNavController.navigateBack()

        // Then
        verify {
            mockNavController.popBackStack()
        }
    }

    @Test
    fun `navigateBack should not call popBackStack when no previous entry`() {
        // Given
        every { mockNavController.previousBackStackEntry } returns null

        // When
        mockNavController.navigateBack()

        // Then
        verify(exactly = 0) {
            mockNavController.popBackStack()
        }
    }

    @Test
    fun `navigateToHomeWithClearBackStack should clear back stack and navigate to home`() {
        // When
        mockNavController.navigateToHomeWithClearBackStack()

        // Then
        verify {
            mockNavController.navigate(
                Navigation.Routes.HOME,
                match {
                    it.popUpTo(0) { inclusive = false }
                    true
                }
            )
        }
    }

    @Test
    fun `routes should have correct values`() {
        // Then
        assertEquals("home", Navigation.Routes.HOME)
        assertEquals("profile", Navigation.Routes.PROFILE)
        assertEquals("settings", Navigation.Routes.SETTINGS)
        assertEquals("detail/{itemId}", Navigation.Routes.DETAIL)
        assertEquals("detail", Navigation.Routes.DETAIL_WITHOUT_PARAM)
    }
}

