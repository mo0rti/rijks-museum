package com.orangeocean.rijksmuseum.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orangeocean.rijksmuseum.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var museumFragmentFactory: MuseumFragmentFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.fragmentFactory = museumFragmentFactory
  }
}