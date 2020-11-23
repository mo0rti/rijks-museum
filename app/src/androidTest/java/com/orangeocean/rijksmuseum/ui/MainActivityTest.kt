package com.orangeocean.rijksmuseum.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.di.CacheModule
import com.orangeocean.rijksmuseum.di.NetworkModule
import com.orangeocean.rijksmuseum.di.RepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@UninstallModules(RepositoryModule::class, NetworkModule::class, CacheModule::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun ui_should_displayed_correctly() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.container)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.fragment_art_collection)).check(ViewAssertions.matches(isDisplayed()))
    }
}
