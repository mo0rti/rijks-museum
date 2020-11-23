package com.orangeocean.rijksmuseum.data.repository

import com.orangeocean.rijksmuseum.domain.common.DataState
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow

interface ArtObjectRepository {
    suspend fun refresh(artistName: String): Flow<DataState<List<ArtObject>>>
    suspend fun getArtObjects(artistName: String): Flow<DataState<List<ArtObject>>>
}