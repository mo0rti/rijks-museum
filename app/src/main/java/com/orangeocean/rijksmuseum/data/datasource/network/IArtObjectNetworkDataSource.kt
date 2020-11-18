package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject

interface IArtObjectNetworkDataSource {

    suspend fun get(artistName: String): List<ArtObject>
}