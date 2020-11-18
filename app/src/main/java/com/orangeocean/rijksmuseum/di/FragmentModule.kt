package com.orangeocean.rijksmuseum.di

import androidx.fragment.app.FragmentFactory
import com.orangeocean.rijksmuseum.ui.MuseumFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FragmentModule {

    @Singleton
    @Provides
    fun provideFragmentFactory(): FragmentFactory {
        return MuseumFragmentFactory()
    }
}



