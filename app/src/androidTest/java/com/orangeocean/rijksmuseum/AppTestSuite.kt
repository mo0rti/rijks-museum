package com.orangeocean.rijksmuseum

import com.orangeocean.rijksmuseum.ui.UITestSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    UITestSuite::class,
)

class AppTestSuite