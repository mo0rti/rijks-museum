package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject

open class ArtObjectNetworkDataSourceMock: ArtObjectNetworkDataSource {

    override suspend fun get(artistName: String): List<ArtObject> {
        return listOf()
    }
}