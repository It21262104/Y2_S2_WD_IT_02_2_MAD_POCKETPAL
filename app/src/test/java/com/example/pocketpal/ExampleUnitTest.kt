package com.example.pocketpal

import android.content.Intent
import org.junit.Test

import org.junit.Assert.*
import org.robolectric.Robolectric

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    //unit test
    @Test
    fun `test calculateTotal`() {
        // Create mock TextViews with test values
        val tvCal1 = MockTextView("10.0")
        val tvCal2 = MockTextView("20.0")
        val tvCal3 = MockTextView("30.0")
        val tvCal4 = MockTextView("40.0")
        val tvCal5 = MockTextView("50.0")

        // Create a mock TextView for tvTotal
        val tvTotal = MockTextView("")

        // Create an instance of the class containing calculateTotal() method
        val myClass = MyClass(tvCal1, tvCal2, tvCal3, tvCal4, tvCal5, tvTotal)

        // Call the function
        myClass.calculateTotal()

        // Assert that the total value is correct
        assertEquals("150.0", tvTotal.text)
    }

    // Define a mock TextView class to test with
    class MockTextView(private val value: String) {
        var text: String = value // Define the text property

        override fun toString(): String {
            return text
        }
    }

    // Define a class that contains the calculateTotal() function and TextViews
    class MyClass(
        val tvCal1: ExampleUnitTest.MockTextView,
        val tvCal2: ExampleUnitTest.MockTextView,
        val tvCal3: ExampleUnitTest.MockTextView,
        val tvCal4: ExampleUnitTest.MockTextView,
        val tvCal5: ExampleUnitTest.MockTextView,
        val tvTotal: ExampleUnitTest.MockTextView // Define tvTotal TextView
    ) {
        fun calculateTotal() {
            val total = tvCal1.text.toString().toDouble() +
                    tvCal2.text.toString().toDouble() +
                    tvCal3.text.toString().toDouble() +
                    tvCal4.text.toString().toDouble() +
                    tvCal5.text.toString().toDouble()
            tvTotal.text = total.toString()
        }

        // Define a mock TextView class to use for testing
        class MockTextView(private val value: String) {
            var text: String = value // Define the text property

            override fun toString(): String {
                return text
            }
        }
    }
}