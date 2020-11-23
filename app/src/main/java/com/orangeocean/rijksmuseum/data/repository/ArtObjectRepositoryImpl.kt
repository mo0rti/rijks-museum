package com.orangeocean.rijksmuseum.data.repository

import com.orangeocean.rijksmuseum.data.datasource.cache.artobject.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.domain.common.DataState
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.utils.AppLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class ArtObjectRepositoryImpl
@Inject
constructor (
    private val cacheDataSource: ArtObjectCacheDataSource,
    private val networkDataSource: ArtObjectNetworkDataSource
): ArtObjectRepository {
    override suspend fun refresh(artistName: String): Flow<DataState<List<ArtObject>>> =
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

    override suspend fun getArtObjects(artistName: String): Flow<DataState<List<ArtObject>>> =
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