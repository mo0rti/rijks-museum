package com.orangeocean.rijksmuseum.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.launchFragmentInHiltContainer
import com.orangeocean.rijksmuseum.ui.artcollection.ArtCollectionFragment
import com.orangeocean.rijksmuseum.ui.artobject.ArtObjectFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class ArtCollectionFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun ui_should_displayed_correctly() {
        launchFragmentInHiltContainer<ArtCollectionFragment>()
        onView(ViewMatchers.withId(R.id.fragment_art_collection)).check(matches(ViewMatchers.isDisplayed()))
    }
}