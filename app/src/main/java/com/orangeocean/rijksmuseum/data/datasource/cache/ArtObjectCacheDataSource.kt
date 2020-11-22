package com.orangeocean.rijksmuseum.data.datasource.cache

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.cache.artobject.ArtObjectCacheService
import com.orangeocean.rijksmuseum.service.cache.artobject.IArtObjectCacheService
import com.orangeocean.rijksmuseum.service.cache.mappers.ArtObjectCacheMapper
import javax.inject.Inject

class ArtObjectCacheDataSource
@Inject
constructor(
    private val cacheService: IArtObjectCacheService,
    private val cacheMapper: ArtObjectCacheMapper
): IArtObjectCacheDataSource {

    override suspend fun insert(artObject: ArtObject): Long {
        return cacheService.insert(cacheMapper.toEntity(artObject))
    }

    override suspend fun get(artistName: String): List<ArtObject> {
        return cacheMapper.toDomainModelList(cacheService.get(artistName))
    }
}
