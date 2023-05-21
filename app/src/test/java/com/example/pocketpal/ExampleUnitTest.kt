package com.example.pocketpal

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoalsDetailPageTest {

    private lateinit var goalsDetailPage: GoalsDetailPage

    @Before
    fun setUp() {
        goalsDetailPage = GoalsDetailPage()
    }

    @Test
    fun testCalculateSavingsNeededPerMonth() {
        // Arrange
        val amount = 5000
        val duration = 5
        val expectedSavingsPerMonth = 1000.00


        // Act
        val actualSavingsPerMonth = goalsDetailPage.calculateSavingsNeededPerMonth(amount, duration)

        // Assert
        assertEquals(expectedSavingsPerMonth, actualSavingsPerMonth, 0.0)
    }
}



//class GoalsAddPageTest {
//
//    @Test
//    fun validateInput_ValidInput_ReturnsTrue() {
//        // Arrange
//        val goalsAddPage = GoalsAddPage()
//        val title = "Save for Vacation"
//        val amount = 5000.0
//        val duration = 12
//        val targetDate = "01/12/2023"
//
//        // Act
//        val result = goalsAddPage.validateInput(title, amount, duration, targetDate)
//
//        // Assert
//        assertTrue(result)
//    }
//
//    @Test
//    fun validateInput_InvalidAmount_ReturnsFalse() {
//        // Arrange
//        val goalsAddPage = GoalsAddPage()
//        val title = "Save for Vacation"
//        val amount = "InvalidAmount"
//        val duration = 12
//        val targetDate = "01/12/2023"
//
//        // Act
//        val result = goalsAddPage.validateInput(title, amount, duration, targetDate)
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun validateInput_InvalidDuration_ReturnsFalse() {
//        // Arrange
//        val goalsAddPage = GoalsAddPage()
//        val title = "Save for Vacation"
//        val amount = 5000.0
//        val duration = "InvalidDuration"
//        val targetDate = "01/12/2023"
//
//        // Act
//        val result = goalsAddPage.validateInput(title, amount, duration, targetDate)
//
//        // Assert
//        assertFalse(result)
//    }
//}
