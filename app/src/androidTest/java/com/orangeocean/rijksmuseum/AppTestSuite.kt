package com.orangeocean.rijksmuseum

import com.orangeocean.rijksmuseum.ui.MainActivityTest
import com.orangeocean.rijksmuseum.ui.NavigationTest
import com.orangeocean.rijksmuseum.ui.ArtCollectionFragmentTest
import com.orangeocean.rijksmuseum.ui.UITestSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    UITestSuite::class,
)

class AppTestSuite