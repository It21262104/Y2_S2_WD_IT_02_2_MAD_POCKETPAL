package com.example.pocketpal

import android.os.Build
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pocketpal.adapters.BillAdapter
import com.example.pocketpal.adapters.CreditAdapter
import com.example.pocketpal.models.BillModel
import com.example.pocketpal.models.CreditModel
import com.example.pocketpal.models.PaidBillModel
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

//@Config(sdk = intArrayOf(Build.VERSION_CODES.P))

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

    //IT21261732
    @Mock
    //lateinit var onBillClickListener: BillAdapter.onBillClickListener

    private lateinit var billList: ArrayList<BillModel>
    private lateinit var billAdapter: BillAdapter

    @Before
    fun setUp() {
        billList = ArrayList()
        billList.add(BillModel("Electricity", 200.0, "2023-05-10"))
        billList.add(BillModel("Internet", 100.0, "2023-05-20"))
        billAdapter = BillAdapter(billList)
        goalsDetailPage = GoalsDetailPage()
    }

    @Test
    fun getItemCount() {
        assertEquals(billList.size, billAdapter.itemCount)
    }

    @Test
    fun testCalculateTotalAmount() {
        val billList = arrayListOf(
            BillModel("bill1", 100.00, "category1", "January", "2023-01-01"),
            BillModel("bill2", 50.00, "category2", "January", "2023-01-05"),
            BillModel("bill3", 75.00, "category1", "February", "2023-02-01")
        )

        var totalAmount = 0.00
        for (bill in billList) {
            totalAmount += bill.amount ?: 0.00
        }

        assertEquals(225.00, totalAmount, 0.001)
    }

    @Test
    fun calculateTotalAmount() {
        val pbillList = arrayListOf<PaidBillModel>()
        pbillList.add(PaidBillModel("bill1", 100.00, "electricity", "Jan", "2022-01-01"))
        pbillList.add(PaidBillModel("bill2", 50.00, "gas", "Jan", "2022-01-09"))
        pbillList.add(PaidBillModel("bill3", 75.00, "water", "Feb", "2022-02-07"))

        var totalAmount = 0.00
        for (pbill in pbillList) {
            totalAmount += pbill.amount ?: 0.00
        }

        assertEquals(225.00, totalAmount, 0.001)
    }


    //IT21262104
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

    private lateinit var goalsDetailPage: GoalsDetailPage



    @Test
    fun testCalculateSavingsNeededPerMonth() {
        // Arrange
        val amount = 5000
        val duration = 5
        val expectedSavingsPerMonth = 1000.0

        // Act
        val actualSavingsPerMonth = goalsDetailPage.calculateSavingsNeededPerMonth(amount, duration)

        // Assert
        assertEquals(expectedSavingsPerMonth, actualSavingsPerMonth, 0.0)
    }


}