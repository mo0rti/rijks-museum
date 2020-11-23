package com.orangeocean.rijksmuseum.data.datasource.cache.artobject

import com.orangeocean.rijksmuseum.domain.model.ArtObject

open class ArtObjectCacheDataSourceMock: ArtObjectCacheDataSource {

    override suspend fun insert(artObject: ArtObject): Long {
        return 1
    }

    override suspend fun get(artistName: String): List<ArtObject> {
        return listOf(ArtObject("1", "title 1", "thumbnail 1",
            "image url 1", "artist 1", "description 1"))
    }
}
