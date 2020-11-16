package com.orangeocean.rijksmuseum.di

import com.orangeocean.rijksmuseum.cache.dao.ArtObjectDao
import com.orangeocean.rijksmuseum.cache.mappers.ArtObjectCacheMapper
import com.orangeocean.rijksmuseum.network.ArtCollectionApi
import com.orangeocean.rijksmuseum.network.mappers.ArtObjectNetworkMapper
import com.orangeocean.rijksmuseum.repository.ArtObjectRepository
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
    fun provideArtObjectRepository(
        artObjectDao: ArtObjectDao,
        artCollectionApi: ArtCollectionApi,
        artObjectNetworkMapper: ArtObjectNetworkMapper,
        artObjectCacheMapper: ArtObjectCacheMapper
    ): ArtObjectRepository {
        return ArtObjectRepository(
            artObjectDao,
            artCollectionApi,
            artObjectNetworkMapper,
            artObjectCacheMapper
        )
    }
}