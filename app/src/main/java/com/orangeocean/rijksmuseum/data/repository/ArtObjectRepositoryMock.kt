package com.orangeocean.rijksmuseum.data.repository

import com.orangeocean.rijksmuseum.domain.common.DataState
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class ArtObjectRepositoryMock: ArtObjectRepository {
    override suspend fun refresh(artistName: String): Flow<DataState<List<ArtObject>>> = flow {
        emit(DataState.Loading)
        delay(10)
        emit(DataState.Success(listOf(ArtObject("1", "title 1", "thumbnail 1",
            "image url 1", "artist 1", "description 1"))))
    }

    override suspend fun getArtObjects(artistName: String): Flow<DataState<List<ArtObject>>> = flow {
        emit(DataState.Loading)
        delay(10)
        emit(DataState.Success(listOf(ArtObject("1", "title 1", "thumbnail 1",
            "image url 1", "artist 1", "description 1"))))
    }
}