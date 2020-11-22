package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.network.artcollection.ArtCollectionNetworkService
import com.orangeocean.rijksmuseum.service.network.mappers.ArtObjectNetworkMapper

open class ArtObjectNetworkMockDataSource: IArtObjectNetworkDataSource {

    override suspend fun get(artistName: String): List<ArtObject> {
        return listOf()
    }
}