package com.orangeocean.rijksmuseum.data.repository

import com.orangeocean.rijksmuseum.data.datasource.cache.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.state.DataState
import com.orangeocean.rijksmuseum.utils.AppLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class ArtObjectRepository
@Inject
constructor (
    private val cacheDataSource: ArtObjectCacheDataSource,
    private val networkDataSource: ArtObjectNetworkDataSource,
) {
    suspend fun refresh(artistName: String): Flow<DataState<List<ArtObject>>> =
        flow {
            emit(DataState.Loading)
            try {
                val networkArtObjects = networkDataSource.get(artistName);
                for (artObject in networkArtObjects) {
                    cacheDataSource.insert(artObject)
                }
                val cachedArtObjects = cacheDataSource.get(artistName);
                emit(DataState.Success(cachedArtObjects));
            } catch (ex: Exception) {
                AppLogger.logError("Data refresh error", ex)
                emit(DataState.Error(Exception("An error has been occurred during fetching data")))
            }
        }

    suspend fun getArtObjects(artistName: String): Flow<DataState<List<ArtObject>>> =
        flow {
            emit(DataState.Loading)
            try {
                val data = getTheData(artistName)
                emit(DataState.Success(data));
            } catch (ex: Exception) {
                AppLogger.logError("Data fetch error", ex)
                emit(DataState.Error(Exception("An error has been occurred during fetching data")))
            }
    }

    private suspend fun getTheData(artistName: String): List<ArtObject> {
        var cachedArtObjects = cacheDataSource.get(artistName);
        if (cachedArtObjects.isEmpty()) {
            val networkArtObjects = networkDataSource.get(artistName);
            for (artObject in networkArtObjects) {
                cacheDataSource.insert(artObject)
            }
            cachedArtObjects = cacheDataSource.get(artistName);
        }
        return cachedArtObjects;
    }
}