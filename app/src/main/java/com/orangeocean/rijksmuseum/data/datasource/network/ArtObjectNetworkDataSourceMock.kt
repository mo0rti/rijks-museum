package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject

open class ArtObjectNetworkDataSourceMock: ArtObjectNetworkDataSource {

    override suspend fun get(artistName: String): List<ArtObject> {
        return listOf(ArtObject("1", "title 1", "thumbnail 1",
            "image url 1", "artist 1", "description 1"))
    }
}