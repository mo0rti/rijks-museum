package com.orangeocean.rijksmuseum.data.datasource.cache

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.cache.artobject.ArtObjectCacheService
import com.orangeocean.rijksmuseum.service.cache.mappers.ArtObjectCacheMapper

class ArtObjectCacheDataSource
constructor(
    private val cacheService: ArtObjectCacheService,
    private val cacheMapper: ArtObjectCacheMapper
): IArtObjectCacheDataSource {

    override suspend fun insert(artObject: ArtObject): Long {
        return cacheService.insert(cacheMapper.toEntity(artObject))
    }

    override suspend fun get(artistName: String): List<ArtObject> {
        return cacheMapper.toDomainModelList(cacheService.get(artistName))
    }
}
