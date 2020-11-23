package com.orangeocean.rijksmuseum.ui

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.di.CacheModule
import com.orangeocean.rijksmuseum.di.NetworkModule
import com.orangeocean.rijksmuseum.di.RepositoryModule
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.launchFragmentInHiltContainer
import com.orangeocean.rijksmuseum.ui.artobject.ArtObjectFragment
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
class ArtObjectFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun ui_should_displayed_correctly() {
        val bundle = Bundle()
        val artObject = ArtObject(
            "art id",
            "art title",
            "art thumbnail",
            "art image url",
            "art artist name",
            "art object description")
        bundle.putParcelable(ArtObjectFragment.ARG_ART_OBJECT, artObject)

        launchFragmentInHiltContainer<ArtObjectFragment>(
            fragmentArgs = bundle
        )

        onView(withId(R.id.text_title)).check(matches(withText(artObject.title)))
        onView(withId(R.id.text_description)).check(matches(withText(artObject.description)))
    }
}
