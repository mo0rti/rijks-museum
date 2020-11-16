package com.orangeocean.rijksmuseum.repository

import android.util.Log
import com.orangeocean.rijksmuseum.cache.dao.ArtObjectDao
import com.orangeocean.rijksmuseum.cache.mappers.ArtObjectCacheMapper
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.network.ArtCollectionApi
import com.orangeocean.rijksmuseum.network.mappers.ArtObjectNetworkMapper
import com.orangeocean.rijksmuseum.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArtObjectRepository constructor(
    private val artObjectDao: ArtObjectDao,
    private val artCollectionApi: ArtCollectionApi,
    private val artObjectNetworkMapper: ArtObjectNetworkMapper,
    private val artObjectCacheMapper: ArtObjectCacheMapper,
) {
    suspend fun getArtObjects(): Flow<DataState<List<ArtObject>>> = flow {
        emit(DataState.Loading)
        try {
            val networkArtCollection = artCollectionApi.get();
            val artObjects = artObjectNetworkMapper.toDomainModelList(networkArtCollection.artObjects);
            for (artObject in artObjects) {
                artObjectDao.insert(artObjectCacheMapper.toEntity(artObject))
            }
            val cachedArtObjects = artObjectDao.get();
            emit(DataState.Success(artObjectCacheMapper.toDomainModelList(cachedArtObjects)));
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}