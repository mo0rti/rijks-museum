package com.orangeocean.rijksmuseum.data.datasource.cache.artobject

import com.orangeocean.rijksmuseum.domain.model.ArtObject

open class ArtObjectCacheDataSourceMock: ArtObjectCacheDataSource {

    override suspend fun insert(artObject: ArtObject): Long {
        return 0
    }

    override suspend fun get(artistName: String): List<ArtObject> {
        return listOf(ArtObject("1", "t", "", "", "", ""))
    }
}
