package com.orangeocean.rijksmuseum.data.repository

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface IArtObjectRepository {
    suspend fun getArtObjects(artistName: String): Flow<DataState<List<ArtObject>>>
}