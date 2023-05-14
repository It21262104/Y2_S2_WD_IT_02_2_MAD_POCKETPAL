package com.example.pocketpal

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.pocketpal", appContext.packageName)
    }

  //instrumental testing
    @get:Rule
    val activityRule = ActivityScenarioRule(credit_main::class.java)

    @Test
    fun testAddNewExpenseButton(){
        onView(withId(R.id.btnAddNewExpense)).perform(click())
        onView(withId(R.id.btnAddNewExpense)).check(matches(isDisplayed()))
    }

    @Test
    fun testMyExpenseButton(){
        onView(withId(R.id.btnMyExpenses)).perform(click())
        onView(withId(R.id.btnMyExpenses)).check(matches(isDisplayed()))
    }



}