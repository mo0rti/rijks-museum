package com.orangeocean.rijksmuseum.data.repository

import com.orangeocean.rijksmuseum.service.cache.database.ArtObjectDao
import com.orangeocean.rijksmuseum.service.cache.mappers.ArtObjectCacheMapper
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.network.api.ArtCollectionApi
import com.orangeocean.rijksmuseum.service.network.mappers.ArtObjectNetworkMapper
import com.orangeocean.rijksmuseum.common.Constants
import com.orangeocean.rijksmuseum.data.datasource.cache.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class ArtObjectRepository
@Inject
constructor (
    private val cacheDataSource: ArtObjectCacheDataSource,
    private val networkDataSource: ArtObjectNetworkDataSource,
) {
    suspend fun getArtObjects(artistName: String): Flow<DataState<List<ArtObject>>> = flow {
        emit(DataState.Loading)
        try {
            var cachedArtObjects = cacheDataSource.get(artistName);
            if (cachedArtObjects.isEmpty()) {
                val networkArtObjects = networkDataSource.get(artistName);
                for (artObject in networkArtObjects) {
                    cacheDataSource.insert(artObject)
                }
                cachedArtObjects = cacheDataSource.get(artistName);
            }
            emit(DataState.Success(cachedArtObjects));
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}