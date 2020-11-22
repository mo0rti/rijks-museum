package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject

interface ArtObjectNetworkDataSource {

    suspend fun get(artistName: String): List<ArtObject>
}