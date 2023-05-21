package com.example.pocketpal

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Assert.assertEquals
import org.junit.Test




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GoalsDetailPageInstrumentedTest {

    @Test
    fun testSavingsNeededPerMonthCalculation() {
        val amount = 5000
        val duration = 5
        val expectedSavingsPerMonth = 1000.0

        // Launch the activity
        val scenario = ActivityScenario.launch(GoalsDetailPage::class.java)

        // Provide the intent extras
        scenario.onActivity {
            it.intent.putExtra("amount", amount.toString())
            it.intent.putExtra("duration", duration.toString())
        }

        // Perform button click to calculate savings per month
        onView(withId(R.id.button2)).perform(click())

        // Get the resulting savings per month value
        val actualSavingsPerMonthText = onView(withId(R.id.tvCalculation)).toString()
        val actualSavingsPerMonth = actualSavingsPerMonthText.substringAfter(":").trim().toDouble()

        // Assert the calculated savings per month
        assertEquals(expectedSavingsPerMonth, actualSavingsPerMonth, 0.0)

        // Close the activity scenario
        scenario.close()
    }
}



//@RunWith(AndroidJUnit4::class)
//class GoalsAddPageTest {
//
//    private lateinit var goalsAddPage: GoalsAddPage
//
//    @Before
//    fun setUp() {
//        goalsAddPage = GoalsAddPage()
//    }
//
//    @Test
//    fun validateInput_ValidInput_ReturnsTrue() {
//        // Arrange
//        val title = "Save for Vacation"
//        val amount = "5000"
//        val duration = "12"
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
//        val title = "Save for Vacation"
//        val amount = "InvalidAmount"
//        val duration = "12"
//        val targetDate = "01/12/2023"
//
//        // Act
//        val result = goalsAddPage.validateInput(title, amount, duration, targetDate)
//
//        // Assert
//        assertFalse(result)
//    }
//}
//
//@RunWith(AndroidJUnit4::class)
//class GoalsAddPageInstrumentedTest {
//
//    private lateinit var goalsAddPage: GoalsAddPage
//
//    @Before
//    fun setUp() {
//        goalsAddPage = GoalsAddPage()
//    }
//
//    @Test
//    fun validateInput_ValidInput_ReturnsTrue() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//
//        // Arrange
//        val title = "Save for Vacation"
//        val amount = "5000"
//        val duration = "12"
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
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//
//        // Arrange
//        val title = "Save for Vacation"
//        val amount = "InvalidAmount"
//        val duration = "12"
//        val targetDate = "01/12/2023"
//
//        // Act
//        val result = goalsAddPage.validateInput(title, amount, duration, targetDate)
//
//        // Assert
//        assertFalse(result)
//    }
//}
