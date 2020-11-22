package com.orangeocean.rijksmuseum.di

import com.orangeocean.rijksmuseum.data.datasource.cache.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.cache.IArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.IArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.data.repository.IArtObjectRepository
import com.orangeocean.rijksmuseum.utils.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArtObjectRepository (
        cacheDataSource: IArtObjectCacheDataSource,
        networkDataSource: IArtObjectNetworkDataSource,
        networkConnectivity: NetworkConnectivity
    ): IArtObjectRepository {
        return ArtObjectRepository(cacheDataSource, networkDataSource/*, networkConnectivity*/)
    }
}
