package com.orangeocean.rijksmuseum.ui

//import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.orangeocean.rijksmuseum.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    fun test_activity_in_view() {
        //ActivityScenario.launch(MainActivity::class.java)
        //Espresso.onView(withId(R.id.container)).check(ViewAssertions.matches(isDisplayed()))
    }
}
