package com.example.pocketpal

import android.os.Build
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pocketpal.adapters.CreditAdapter
import com.example.pocketpal.models.CreditModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.Robolectric
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */




@RunWith(RobolectricTestRunner::class)

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))

class ExampleUnitTest {
    @Test
    fun testCalculateTotalExpense() {
        println("Running testCalculateTotalExpense() test...")
        // Create an instance of the activity
        val activity = Robolectric.buildActivity(view_per_month::class.java).create().get()
        // Set the values of the text views to be used in the calculation
        activity.amount1.text = "10"
        activity.amount2.text = "20"
        activity.amount3.text = "30"
        activity.amount4.text = "40"
        // Call the function to calculate the total expense
        activity.calculateTotalExpense()
        // Check that the total expense is calculated correctly
        assertEquals("100.0", activity.totalEx.text.toString())
    }


    @Test
    fun testCalculataRemainingCredits() {
        println("Running testCalculateRemainingCredits() test...")
        // Create an instance of the activity
        val activity = Robolectric.buildActivity(view_per_month::class.java).create().get()
        // Set the values of the text views to be used in the calculation
        activity.tvShowAmount.text = "200"
        activity.totalEx.text = "100"
        // Call the function to calculate the remaining credits
        activity.calculataRemainingCredits()
        // Check that the remaining credits are calculated correctly
        assertEquals("100.0", activity.RemCredit.text.toString())
     }

}