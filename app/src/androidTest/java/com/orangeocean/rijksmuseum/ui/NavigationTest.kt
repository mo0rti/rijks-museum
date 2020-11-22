package com.orangeocean.rijksmuseum.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.launchFragmentInHiltContainer
import com.orangeocean.rijksmuseum.ui.artcollection.ArtCollectionFragment
import com.orangeocean.rijksmuseum.ui.artcollection.ArtCollectionRecyclerAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class NavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun navigate_to_artObject_from_artObjectCollection() {
        ActivityScenario.launch(MainActivity::class.java)

        launchFragmentInHiltContainer<ArtCollectionFragment>()

        GlobalScope.launch {
            delay(2000)

            onView(withId(R.id.recycler_view_art_collection))
                .perform(actionOnItemAtPosition<ArtCollectionRecyclerAdapter.ArtCollectionVH>
                    (1, ViewActions.click()))

            onView(withId(R.id.fragment_art_object)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}