package com.orangeocean.rijksmuseum.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    NavigationTest::class,
    ArtCollectionFragmentTest::class,
    ArtObjectFragmentTest::class,
)

class UITestSuite