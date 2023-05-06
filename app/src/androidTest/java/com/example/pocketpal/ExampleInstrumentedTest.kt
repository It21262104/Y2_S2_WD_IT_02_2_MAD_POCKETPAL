package com.example.pocketpal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
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

    //instrumented testing
    @get:Rule
    val activityRule = ActivityScenarioRule(Home::class.java)

    @Test
    fun testRegisterButton() {
        onView(withId(R.id.btnRegisterHome)).perform(click())
        onView(withId(R.id.btnRegisterButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginButton() {
        onView(withId(R.id.btnLoginRegister)).perform(click())
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }
}