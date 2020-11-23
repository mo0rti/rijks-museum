package com.orangeocean.rijksmuseum.fake

import com.orangeocean.rijksmuseum.data.datasource.cache.artobject.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.cache.artobject.ArtObjectCacheDataSourceMock
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSourceMock
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepositoryMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FakeModules {
    @Singleton
    @Provides
    fun provideArtObjectNetworkDataSource(): ArtObjectNetworkDataSource {
        return ArtObjectNetworkDataSourceMock()
    }

    @Singleton
    @Provides
    fun provideArtObjectCacheDataSource(): ArtObjectCacheDataSource {
        return ArtObjectCacheDataSourceMock()
    }

    @Singleton
    @Provides
    fun provideArtObjectRepository (): ArtObjectRepository {
        return ArtObjectRepositoryMock()
    }
}