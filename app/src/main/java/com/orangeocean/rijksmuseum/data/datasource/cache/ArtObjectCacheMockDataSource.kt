package com.orangeocean.rijksmuseum.data.datasource.cache

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.cache.artobject.ArtObjectCacheService
import com.orangeocean.rijksmuseum.service.cache.mappers.ArtObjectCacheMapper

open class ArtObjectCacheMockDataSource: IArtObjectCacheDataSource {

    override suspend fun insert(artObject: ArtObject): Long {
        return 0
    }

    override suspend fun get(artistName: String): List<ArtObject> {
        return listOf(ArtObject("1", "t", "", "", "", ""))
    }
}
